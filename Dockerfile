FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
ENV PORT 8080
EXPOSE 8080
COPY ./target/TaskBackend-0.0.1-SNAPSHOT-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
