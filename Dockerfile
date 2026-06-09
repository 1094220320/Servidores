# =============================================
# STAGE 1: Compilar con Maven
# =============================================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

# =============================================
# STAGE 2: Imagen final liviana
# =============================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

RUN addgroup -S techgear && adduser -S techgear -G techgear
COPY --from=build /app/target/techgear-1.0.0.jar app.jar
RUN chown techgear:techgear app.jar
USER techgear

EXPOSE 8080

# Estas variables se sobreescriben al hacer docker run o en docker-compose
ENV DB_HOST=localhost \
    DB_PORT=5432 \
    DB_NAME=TechGear \
    DB_USER=postgres \
    DB_PASSWORD=postgres \
    JAVA_OPTS="-Xms256m -Xmx512m"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
