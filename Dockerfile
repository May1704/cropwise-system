# ---- Parte 1: Build  ----
#Se baja una imagen oficial de Maven con Java 21 (maven:3.9.11-eclipse-temurin-21).
FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /src

# Copia del código
COPY . .

# Descarga dependencias (mvn dependency:go-offline) para que no tenga que bajarlas cada vez.
RUN mvn dependency:go-offline

# Genera el JAR
RUN mvn clean package -DskipTests

# ---- Parte 2: Run (Aquí es donde se crea mi archivo target/xxx.jar) ----
#Usa una imagen mucho más liviana de Java (eclipse-temurin:21-jre)
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia el JAR generado en el paso anterior
COPY --from=build /src/target/*.jar app.jar

# Expone el puerto 8080. por defecto el de Springboot
EXPOSE 8080

# Arranca la app
ENTRYPOINT ["java", "-jar", "app.jar"]