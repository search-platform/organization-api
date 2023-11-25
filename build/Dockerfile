# Этап 1: Сборка приложения
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
# Скачивание зависимостей
RUN mvn dependency:go-offline -B
COPY src ./src
# Сборка приложения
RUN mvn package -DskipTests

# Этап 2: Создание образа для запуска
FROM openjdk:22-jdk-slim-bullseye
WORKDIR /app
# Копирование собранного jar-файла из этапа сборки
COPY --from=build /app/target/*.jar app.jar
# Указание точки входа и команды
ENTRYPOINT ["java","-jar","/app/app.jar"]