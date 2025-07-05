# -------------------------------------------
# 🏗️ Stage 1: Build Stage
# -------------------------------------------
    
FROM maven:3.9.10-eclipse-temurin-17 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy Maven wrapper and pom.xml first for layer caching
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download dependencies to speed up future builds
RUN ./mvnw dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the project JAR (skip tests during Docker build)
RUN ./mvnw clean package -DskipTests

# -------------------------------------------
# 🚀 Stage 2: Runtime Stage
# -------------------------------------------

FROM eclipse-temurin:17-jre-jammy

# Set the working directory for the app
WORKDIR /app

# 🛡️ Create a non-root user for running the app
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring

# Copy only the built JAR from the builder stage
# NOTE: Replace the wildcard if your JAR has a specific name
COPY --from=builder /app/target/notes-service-0.0.1-SNAPSHOT.jar app.jar

# Inform Docker what port the container exposes (optional)
EXPOSE 8080

# Default command to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
    