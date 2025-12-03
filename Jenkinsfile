pipeline {
  agent any

  environment {
    APP_NAME = "user-app"
    IMAGE = "user-app:${env.BUILD_NUMBER}"
    JAR_GLOB = "target/*.jar"
    HOST_PORT = "8081"   // app will be exposed on host:8081 to avoid conflict with Jenkins:8080
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      steps {
        // ensure executable
        sh 'chmod +x mvnw || true'
        sh './mvnw -B clean package'
      }
      post {
        always {
          junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
        }
      }
    }

    stage('Archive') {
      steps {
        archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
      }
    }

    stage('Docker Build & Deploy (if docker)') {
      steps {
        script {
          def jarExists = sh(script: "ls ${JAR_GLOB} 2>/dev/null || true", returnStdout: true).trim()
          if (!jarExists) {
            error "Jar not found in target/ — build failed or produced different output."
          }

          // If docker is present, build image + run container (host:8081 -> container:8080)
          def dockerAvailable = sh(script: 'which docker >/dev/null 2>&1 && echo "yes" || echo "no"', returnStdout: true).trim()
          if (dockerAvailable == 'yes') {
            sh "docker build -t ${IMAGE} ."
            // stop & remove previous container if present
            sh "docker rm -f ${APP_NAME} || true"
            // run in detached mode; map host port 8081 -> container 8080 (Spring default)
            sh "docker run -d --name ${APP_NAME} -p ${HOST_PORT}:8080 ${IMAGE}"
            echo "App container started and mapped to http://localhost:${HOST_PORT}"
          } else {
            // fallback: run jar directly in background (will use server.port=8081)
            sh "pkill -f '${APP_NAME}' || true || true"
            sh "nohup java -jar ${JAR_GLOB} --server.port=${HOST_PORT} > ${APP_NAME}.log 2>&1 &"
            echo "App started via java -jar and reachable at http://localhost:${HOST_PORT}"
          }
        }
      }
    }
  }

  post {
    always {
      echo "Pipeline finished. Check build logs / archived artifacts."
    }
    failure {
      subject: "Build failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}", body: "See Jenkins job for details." // optional
    }
  }
}
