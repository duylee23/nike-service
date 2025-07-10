FROM maven:latest as builder
WORKDIR /product-service
COPY . .
RUN --mount=type=cache,id=maven,target=/root/.m2 \
    mvn clean package -pl product-service -am -DskipTests
FROM openjdk:17-alpine
WORKDIR /product-service
EXPOSE 8080
COPY --from=builder /product-service/product-service/target/*.jar ./product-service.jar
CMD ["java", "-jar" ,"product-service.jar"]