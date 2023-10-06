FROM openjdk:latest
WORKDIR /app
COPY ./target/catalog-ms-0.0.1-SNAPSHOT.jar .

ENV SPRING_DATASOURCE_URL jdbc:mysql://catalog-db:3306/product
ENV SPRING_DATASOURCE_USERNAME root
ENV SPRING_DATASOURCE_PASSWORD Gm1507@#

CMD ["java", "-jar", "catalog-ms-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081