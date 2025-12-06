# Use Java 21 JRE (compatible with your pom.xml: <java.version>21</java.version>)
FROM eclipse-temurin:21-jre-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the built Spring Boot fat JAR into the container
COPY target/SpringBoot_DemoProject-0.0.1-SNAPSHOT.jar app.jar

# Spring Boot default port
EXPOSE 8081

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]

