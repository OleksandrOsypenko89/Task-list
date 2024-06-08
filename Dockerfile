FROM openjdk:17-jdk
COPY target/Todo-list-0.0.1-SNAPSHOT.jar /app/Todo-list-0.0.1-SNAPSHOT.jar
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "Todo-list-0.0.1-SNAPSHOT.jar"]