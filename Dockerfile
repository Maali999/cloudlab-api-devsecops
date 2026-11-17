# Stage 1: Build the Spring Boot application
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn -B clean package -DskipTests


# Stage 2: Run the application
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Create a non-root user
RUN groupadd --system appgroup && \
    useradd --system --gid appgroup --home-dir /app appuser

# Copy the generated JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run as a non-root user
USER appuser

# Application port
EXPOSE 8080

# Start Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
