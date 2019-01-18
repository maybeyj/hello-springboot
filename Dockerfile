FROM openjdk:11
VOLUME /tmp
COPY hello-springboot-0.0.1-SNAPSHOT.jar /usr/local/
ENTRYPOINT ["java","-jar","/usr/local/hello-springboot-0.0.1-SNAPSHOT.jar"]