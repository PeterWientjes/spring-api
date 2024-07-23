# Stage 1: Build the application
FROM openjdk:21-buster as build
WORKDIR /workspace/app

# Install xargs
RUN apt-get update && apt-get install -y findutils


# Copy the Gradle configuration files
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Copy the source code
COPY src src

#This is a fix for a windowsbug with ./gradlew (can be ignored when not running windows)
RUN apt-get install dos2unix
RUN dos2unix ./gradlew

# Grant execute permission for the gradle wrapper and build the JAR file using arguments
RUN chmod +x ./gradlew && \
    ./gradlew --no-daemon --no-watch-fs bootJar -x test

# Stage 2: Prepare the runtime container
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /workspace/app/build/libs/*.jar app.jar

# Set the entrypoint
ENTRYPOINT ["java","-jar","/app/app.jar"]
