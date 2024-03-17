# 사용할 Java 버전 지정
FROM openjdk:17-jdk-slim

# 애플리케이션 파일 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 실행 명령어
ENTRYPOINT ["java","-jar","/app.jar"]
