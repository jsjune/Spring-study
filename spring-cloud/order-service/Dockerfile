FROM openjdk:11-jre-slim
VOLUME /tmp
COPY build/libs/order-service-0.0.1-SNAPSHOT.jar OrderService.jar
ENTRYPOINT ["java","-jar","OrderService.jar"]