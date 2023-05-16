# Use a base image with Java and Alpine Linux
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle Wrapper files
COPY gradlew /app/
COPY gradle /app/gradle

# Copy the build configuration files
COPY build.gradle /app/
COPY settings.gradle /app/

# Copy the source code
COPY src /app/src

# Build the application with the desired Spring profile
ARG SPRING_PROFILE
RUN ./gradlew build

# Copy the executable JAR file to the root of the container
COPY build/libs/task.jar /app/task.jar

# Expose the port on which the Spring Boot application listens
EXPOSE 8080

# Set the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "-Dspring.profiles.active=${SPRING_PROFILE}", "task.jar"]
