FROM openjdk:11-jre-slim
VOLUME /tmp
COPY apiEncryptionKey.jks apiEncryptionKey.jks
COPY build/libs/config-service-0.0.1-SNAPSHOT.jar ConfigService.jar
ENTRYPOINT ["java","-jar","ConfigService.jar"]