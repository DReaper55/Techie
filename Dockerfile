# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container at /app
COPY target/techie-test.jar /app/techie-test.jar

# Specify the command to run the application
CMD ["java", "-jar", "techie-test.jar"]
