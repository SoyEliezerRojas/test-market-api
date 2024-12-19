# Usar una imagen Maven con JDK 17 (estable)
FROM maven:3.8.7-openjdk-17-slim AS build

# Crear el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar el proyecto
RUN mvn clean package -DskipTests

# Crear una imagen final con el JAR compilado
FROM amazoncorretto:18-alpine-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
