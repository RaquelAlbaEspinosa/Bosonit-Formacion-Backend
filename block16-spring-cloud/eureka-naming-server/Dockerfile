FROM openjdk:17-jdk-alpine
EXPOSE  8761
COPY /target/eureka-naming-server-0.0.1-SNAPSHOT.jar /usr/local/lib/
ENTRYPOINT ["java", "-jar", "/usr/local/lib/eureka-naming-server-0.0.1-SNAPSHOT.jar"]