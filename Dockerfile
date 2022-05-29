FROM openjdk:8-jdk-alpine
COPY target/ target/
COPY config.yml target/config.yml
EXPOSE 8080
RUN java -jar target/task-management-1.0-SNAPSHOT.jar db migrate target/config.yml
ENTRYPOINT ["java","-jar","target/task-management-1.0-SNAPSHOT.jar", "server", "target/config.yml"]
