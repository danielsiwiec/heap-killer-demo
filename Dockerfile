FROM gcr.io/distroless/java17-debian11

COPY app/build/libs/app.jar /app.jar

CMD ["/app.jar"]