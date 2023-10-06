FROM openjdk:latest
WORKDIR /app
COPY ./target/shoppingCart-ms-0.0.1-SNAPSHOT.jar .

ENV SPRING_DATASOURCE_URL jdbc:mysql://shopping-cart-db:3306/shoppingCart
ENV SPRING_DATASOURCE_USERNAME root
ENV SPRING_DATASOURCE_PASSWORD Gm1507@#

CMD ["java", "-jar", "shoppingCart-ms-0.0.1-SNAPSHOT.jar"]
EXPOSE 8082