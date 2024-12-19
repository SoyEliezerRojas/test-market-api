# Usar una imagen Maven oficial con una versión genérica y estable de OpenJDK
FROM maven:3.8.5-openjdk-17 AS build

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
FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
