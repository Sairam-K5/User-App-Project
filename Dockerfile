# Use Java 21 JRE (compatible with your pom.xml: <java.version>21</java.version>)
FROM eclipse-temurin:21-jre-alpine

# Set working directory inside the container
WORKDIR /app

# The JAR will be created by Jenkins with:
# mvn -B clean package  -> target/<something>.jar
ARG JAR_FILE=target/*.jar

# Copy the built Spring Boot fat JAR into the container
COPY ${JAR_FILE} app.jar

# Optional: set Spring profile (adjust if you use profiles)
# ENV SPRING_PROFILES_ACTIVE=prod

# Spring Boot default port
EXPOSE 8081

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]

