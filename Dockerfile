# Step 1: Use an official Maven image to build the application
FROM maven:3.8.3-openjdk-17 AS build

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the pom.xml file and download dependencies first (use caching)
COPY pom.xml .

# Step 4: Download Maven dependencies without copying the source code (caching optimization)
RUN mvn dependency:go-offline -B

# Step 5: Copy the rest of the project files
COPY src ./src

# Step 6: Build the Spring Boot application (skipping tests to save time)
WORKDIR /app

#RUN mvn clean install
RUN mvn clean install -DskipTests

# Step 7: Use an official OpenJDK runtime as the base for the final image
FROM openjdk:17-jdk-slim AS runtime

# Step 8: Set the working directory in the container
WORKDIR /app



# Step 9: Copy the JAR file from the build stage to the runtime stage
COPY --from=build /app/target/DockerAdvanceAssigment-1.0-SNAPSHOT.jar /app/DockerDemoAssignment.jar



# Step 11: Command to run the application
ENTRYPOINT ["java", "-jar", "/app/DockerDemoAssignment.jar"]

# Step 10: Expose the application port (default Spring Boot port is 8080)
EXPOSE 8082
