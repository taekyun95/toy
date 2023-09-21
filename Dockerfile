# 빌드 스테이지
FROM gradle:jdk11 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle clean build

# 실행 스테이지
FROM openjdk:11-jre-slim
EXPOSE 8080
COPY --from=build /app/build/libs/toy-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
