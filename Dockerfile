#syntax=docker/dockerfile:1
FROM arm64v8/eclipse-temurin:11
RUN groupadd --gid 10001 spring && useradd --gid 10001 --uid 10001 --home-dir /spring spring
USER spring:spring
EXPOSE 8080
ARG JAR_FILE=app/build/libs/*jar
COPY ${JAR_FILE} PeopleApplication.jar 
ENTRYPOINT ["java","-jar","/PeopleApplication.jar"]
