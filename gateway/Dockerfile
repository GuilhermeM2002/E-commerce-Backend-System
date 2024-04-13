FROM openjdk:17-alpine
WORKDIR /app
COPY ./target/gateway_jar/gateway.jar .
CMD ["java", "-jar", "gateway.jar"]
EXPOSE 3002

