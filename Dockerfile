FROM adoptopenjdk/openjdk11:alpine-jre
COPY /target/contactmanager-0.0.1-SNAPSHOT.jar contact-manager.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","/contact-manager.jar" ]