FROM openjdk:8-jre-alpine

EXPOSE 8080


COPY ./target/helloworld.war /usr/app/
WORKDIR /usr/app

CMD java -jar helloworld.jar
