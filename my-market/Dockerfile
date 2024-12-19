# Usar una imagen Maven para construir el proyecto
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copiar pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar el proyecto
COPY src ./src
RUN mvn clean package -DskipTests

# Crear la imagen final con Amazon Corretto
FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
COPY --from=build /app/target/my-market-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
