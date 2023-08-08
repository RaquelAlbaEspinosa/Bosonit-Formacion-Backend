FROM openjdk:17-jdk-alpine
VOLUME /tmp
ENV TOPICNAMER mytopic_1
ENV TOPICNAME2 myGroup
ENV GROUPID myGroup
COPY /target/pruebaKafka-0.0.1-SNAPSHOT.jar /tmp/
COPY /javaapp.sh /tmp/
EXPOSE 8080
ENTRYPOINT "/tmp/javaapp.sh"