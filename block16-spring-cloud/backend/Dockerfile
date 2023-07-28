FROM openjdk:17-jdk-alpine
EXPOSE  8081
COPY /target/backend-0.0.1-SNAPSHOT.jar /usr/local/lib/
ENTRYPOINT ["java", "-jar", "/usr/local/lib/backend-0.0.1-SNAPSHOT.jar"]