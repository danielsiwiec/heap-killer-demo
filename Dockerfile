FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y procps htop

COPY app/build/libs/app.jar /app.jar

CMD ["java", "-XX:NativeMemoryTracking=summary", "-jar", "/app.jar"]