FROM openjdk:8-jre-alpine

EXPOSE 8080


COPY ./target/helloworld.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "helloworld.jar"]
