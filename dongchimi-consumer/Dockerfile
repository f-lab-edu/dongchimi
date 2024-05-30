FROM openjdk:21-jdk

WORKDIR /app
COPY build/libs/dongchimi-*.jar /app/consumer.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/api.jar"]