FROM adoptopenjdk/openjdk11:alpine-jre

RUN apk update
RUN apk upgrade

RUN java -version

RUN apk add --no-cache tzdata
ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir address
ADD target/*.jar address/app.jar

EXPOSE 8080
ENTRYPOINT java -jar address/app.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE}