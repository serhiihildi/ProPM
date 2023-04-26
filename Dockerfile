FROM maven:3.8.1-jdk-11-slim AS build
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /usr/src/app/target/propm-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
CMD ["java", "-jar", "propm-0.0.1-SNAPSHOT.jar"]
