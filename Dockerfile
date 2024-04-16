FROM maven:3.8.7-eclipse-temurin-19-alpine
WORKDIR /app
COPY . .
RUN mvn package -DskipTests
EXPOSE 8080
CMD ["sh", "-c", "java -jar target/hotelbooker-0.0.1-SNAPSHOT.jar"]

