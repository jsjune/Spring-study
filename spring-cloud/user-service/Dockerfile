FROM openjdk:11-jre-slim
VOLUME /tmp
COPY build/libs/user-service-0.0.1-SNAPSHOT.jar UserService.jar
ENTRYPOINT ["java","-jar","UserService.jar"]