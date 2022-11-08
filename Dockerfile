FROM openjdk:17-jdk-slim

COPY app/build/libs/app.jar /app.jar

CMD ["java", "-jar", "/app.jar"]