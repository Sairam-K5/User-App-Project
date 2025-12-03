# Use OpenJDK base image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar file to container
COPY target/SpringBoot_DemoProject-0.0.1-SNAPSHOT.jar app.jar

# Expose port (update if needed)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
