FROM openjdk:8-jdk-alpine

RUN echo -e "http://nl.alpinelinux.org/alpine/v3.5/main\nhttp://nl.alpinelinux.org/alpine/v3.5/community" > /etc/apk/repositories

RUN apk add --update fontconfig ttf-dejavu

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Xmx4096m","-jar","/app.jar","-Dspring.profiles.active=prod"]