# Use a lightweight JDK image for building
FROM eclipse-temurin:17-jdk AS build

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# If using Maven:
RUN ./mvnw clean package -DskipTests

# If using Gradle (uncomment instead):
# RUN ./gradlew clean build -x test

# Use a smaller JRE image for running
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar
# If Gradle, use /app/build/libs/*.jar instead

# Expose port (default Spring Boot port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
