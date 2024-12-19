# Usar una imagen Maven para construir el proyecto
FROM maven:3.8.7-eclipse-temurin-18-alpine AS build
WORKDIR /app
# Copiar el archivo pom.xml y las fuentes del proyecto
COPY pom.xml .
COPY src ./src
# Ejecutar la compilación de Maven
RUN ./mvnw clean package -DskipTests

# Crear la imagen final con Amazon Corretto
FROM amazoncorretto:18-alpine-jdk
WORKDIR /app
# Copiar el JAR generado en la etapa anterior
COPY --from=build /app/target/my-market-0.0.1-SNAPSHOT.jar app.jar
# Definir el punto de entrada
ENTRYPOINT ["java", "-jar", "/app.jar"]
