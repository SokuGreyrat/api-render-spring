# ====== Build stage ======
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos primero lo necesario para cache
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw

# Descarga dependencias (cache)
RUN ./mvnw -B -DskipTests dependency:go-offline

# Copiamos el código y construimos
COPY src ./src
RUN ./mvnw -B -DskipTests package

# ====== Run stage ======
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENV PORT=8080
EXPOSE 10000
CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
