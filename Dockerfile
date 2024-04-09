# Use a base image with Java 17 and Maven 3.6.0
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Build the application with Maven
RUN mvn clean install -DskipTests

# Use a lightweight Java 17 image for running the application
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build stage to the runtime image
COPY --from=build /app/target/sample-gke-0.0.1-SNAPSHOT.jar .

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "sample-gke-0.0.1-SNAPSHOT.jar"]
