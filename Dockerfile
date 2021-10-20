# 1. Stage for generate jar
FROM openjdk:8-jdk-alpine as api-builder
COPY ./challenge-api /build
WORKDIR /build

RUN ./mvnw package

# 2. Launch APP
FROM openjdk:8-jdk-alpine
COPY --from=api-builder /build/target/* /app/
WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "challenge-0.0.1-SNAPSHOT.jar"]