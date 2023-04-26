FROM maven:3.8.3-openjdk-17-slim AS build
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /usr/src/app/target/ProPM-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
CMD ["java", "-jar", "ProPM-1.0-SNAPSHOT.jar"]
