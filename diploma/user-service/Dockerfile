FROM maven:3.8.1-openjdk-17-slim AS builder
RUN mkdir app
COPY . app/
WORKDIR app/
RUN mvn install
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
EXPOSE 8080
COPY --from=builder app/target/user-service.jar user-service.jar
ENTRYPOINT ["java","-jar","user-service.jar"]