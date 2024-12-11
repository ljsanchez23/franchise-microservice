FROM eclipse-temurin:17.0.12_7-jdk

EXPOSE 8080

WORKDIR /app

COPY ./build/libs/franchise-microservice-0.0.1-SNAPSHOT.jar /app/franchise-microservice.jar

ENTRYPOINT ["java", "-jar", "/app/franchise-microservice.jar"]