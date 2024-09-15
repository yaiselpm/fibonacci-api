# Etapa 1: Construir la aplicación
FROM maven:3.8.5-openjdk-17 AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo pom.xml y resolver las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente de la aplicación
COPY src ./src

# Construir la aplicación (esto generará un archivo .jar en el directorio target)
RUN mvn clean package -DskipTests

# Etapa 2: Crear la imagen final usando una imagen base de JDK más ligera
FROM openjdk:17-jdk-slim

# Establecer un directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo .jar generado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto en el que la aplicación correrá
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
