FROM openjdk:17-alpine
WORKDIR /app
COPY ./target/catalog_ms_jar/catalog-ms.jar .
CMD ["java", "-jar", "catalog-ms.jar"]
EXPOSE 3003