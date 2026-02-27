
WORKDIR /app

file for Maven +
COPY pom.xml .
dockerfile
# Stage
FROM eclipse-temurin:17-jdk AS build# Copy Maven wrapper and pom.xml firstCOPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn

# Downloadaches layers)
RUN dependencies (c ./mvnw dependency:go-offline

# Copy the source code
COPY src src

# Build the application
RUN ./mvnw clean
FROM eclipse-tem package -DskipTestsurin:17-jre

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar

# Stage 2: Run the application app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]