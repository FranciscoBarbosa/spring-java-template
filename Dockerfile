FROM eclipse-temurin:17-jdk-alpine

LABEL authors="francisco.barbosa"

COPY frameworks/target/frameworks-*-jar-with-dependencies.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
