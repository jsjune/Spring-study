FROM openjdk:11-jre-slim
VOLUME /tmp
COPY build/libs/catalogs-service-0.0.1-SNAPSHOT.jar CatalogService.jar
ENTRYPOINT ["java","-jar","CatalogService.jar"]