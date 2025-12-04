pipeline {
    agent any

    environment {
        // ======= CHANGE THESE FOR YOUR PROJECT =======
        // Docker Hub image name: <your-dockerhub-username>/<repo-name>
        IMAGE_NAME = "sairamk5/my-userapp"

        // Tag image with Jenkins build number (or use GIT_COMMIT if you prefer)
        IMAGE_TAG  = "${env.BUILD_NUMBER}"

        // Jenkins credentials:
        // 1) Docker Hub username/password as "Username with password"
        DOCKERHUB_CREDENTIALS_ID = "dockerhub-cred-id"

        // 2) kubeconfig file for your Kubernetes cluster as "Secret file"
        KUBECONFIG_CREDENTIALS_ID = "kubeconfig-cred-id"

        // Kubernetes details
        K8S_NAMESPACE = "default"
        K8S_DEPLOYMENT = "userapp-deployment"   // kubectl get deploy -n <ns>
        K8S_CONTAINER  = "userapp-container"    // container name inside that deployment
    }

    options {
        // Keep console output shorter
        timestamps()
    }

    stages {

        stage('Checkout from Git') {
            steps {
                // If this is a "Pipeline script from SCM" job or Multibranch Pipeline,
                // Jenkins already knows the Git repo; this will clone it:
                checkout scm

                // If you want to hardcode Git instead, comment the above and use:
                // git branch: 'main', url: 'https://github.com/Sairam-K5/User-App-Project.git'
            }
        }

        stage('Build & Test (Maven)') {
            steps {
                sh '''
                    echo "Building Java project with Maven..."
                    mvn -B clean package
                    echo "Maven build + tests completed."
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo "Building Docker image ${IMAGE_NAME}:${IMAGE_TAG}"
                    sh """
                        docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
                    """
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(
                        credentialsId: DOCKERHUB_CREDENTIALS_ID,
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )]) {
                        sh '''
                            echo "Logging in to Docker Hub..."
                            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        '''
                    }

                    sh """
                        echo "Pushing image to Docker Hub..."
                        docker push ${IMAGE_NAME}:${IMAGE_TAG}

                        echo "Tagging and pushing 'latest'..."
                        docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${IMAGE_NAME}:latest
                        docker push ${IMAGE_NAME}:latest
                    """
                }
            }
        }

        stage('Deploy to Kubernetes with kubectl') {
            steps {
                script {
                    // Use the kubeconfig file stored as a Jenkins secret file
                    withCredentials([file(credentialsId: KUBECONFIG_CREDENTIALS_ID, variable: 'KUBECONFIG_FILE')]) {
                        sh '''
                            export KUBECONFIG="$KUBECONFIG_FILE"

                            echo "Updating deployment image in Kubernetes..."
                            kubectl set image deployment/${K8S_DEPLOYMENT} ${K8S_CONTAINER}=${IMAGE_NAME}:${IMAGE_TAG} -n ${K8S_NAMESPACE}

                            echo "Waiting for rollout to finish..."
                            kubectl rollout status deployment/${K8S_DEPLOYMENT} -n ${K8S_NAMESPACE}
                        '''
                    }
                }
            }
        }
    }

    post {
        success {
            echo "✅ Build & deployment successful! Image: ${IMAGE_NAME}:${IMAGE_TAG}"
        }
        failure {
            echo "❌ Build or deployment failed. Check the logs above."
        }
    }
}
