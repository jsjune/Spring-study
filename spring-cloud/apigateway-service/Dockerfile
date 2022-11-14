FROM openjdk:11-jre-slim
VOLUME /tmp
COPY build/libs/apigateway-service-0.0.1-SNAPSHOT.jar ApiGateway.jar
ENTRYPOINT ["java","-jar","ApiGateway.jar"]