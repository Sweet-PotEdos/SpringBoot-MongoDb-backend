# Stage 1: Build the application using Maven
FROM maven:3.8.3-openjdk-11-slim AS build
WORKDIR /.
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Create the final Docker image with the built artifact
FROM openjdk:11-jre-slim

# Step 2: Install MongoDB in the container
# Note: This is just an example of installing MongoDB, and in a real-world scenario, you'd want to use an official MongoDB Docker image and configure it properly.
RUN apt-get update && apt-get install -y mongod

# Step 3: Set environment variables for MongoDB connection (replace with your actual MongoDB connection details)
ENV MONGO_HOST=localhost
ENV MONGO_PORT=27017
ENV MONGO_DB=mydatabase
ENV MONGO_USERNAME=edoardomiccono282
ENV MONGO_PASSWORD=password

# Copy the built artifact from the previous stage
COPY --from=build /.mvn/wrapper/maven-wrapper.jar /maven-wrapper.jar

# Expose the port that your Spring Boot application is running on
EXPOSE 8080

# Set the command to run your Spring Boot application
CMD ["java", "-jar", "com.wirail.DBTest.DbTestApplication"]


