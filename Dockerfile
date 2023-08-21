FROM maven:3.8.3-openjdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM adoptopenjdk/openjdk11:alpine-jre
COPY --from=build /target/contactmanager-0.0.1-SNAPSHOT.jar contact-manager.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","/contact-manager.jar" ]