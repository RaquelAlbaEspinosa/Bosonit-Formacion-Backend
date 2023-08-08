FROM openjdk:17-jdk-alpine
EXPOSE  8080
COPY /target/backend-Front-0.0.1-SNAPSHOT.jar /usr/local/lib/
ENTRYPOINT ["java", "-jar", "/usr/local/lib/backend-Front-0.0.1-SNAPSHOT.jar"]