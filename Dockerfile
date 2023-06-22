# Используем базовый образ с поддержкой Java
FROM openjdk:17-jdk
# Копируем JAR-файл в контейнер
COPY target/Todo-list-0.0.1-SNAPSHOT.jar /app/Todo-list-0.0.1-SNAPSHOT.jar
# Устанавливаем рабочую директорию
WORKDIR /app
# Открываем порт для взаимодействия с приложением
EXPOSE 8080
# Запускаем приложение при старте контейнера
CMD ["java", "-jar", "Todo-list-0.0.1-SNAPSHOT.jar"]