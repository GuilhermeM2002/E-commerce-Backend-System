FROM openjdk:17-alpine
WORKDIR /app
COPY ./target/order_ms_jar/order-ms.jar .
CMD ["java", "-jar", "order-ms.jar"]
EXPOSE 3005
