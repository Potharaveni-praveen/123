FROM openjdk:8-jre-alpine

EXPOSE 8080

RUN mkdir -p /usr/app/

COPY ./target/helloworld.war /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-war", "helloworld.war" ]
