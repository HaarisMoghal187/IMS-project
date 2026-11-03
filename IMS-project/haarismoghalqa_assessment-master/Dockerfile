FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src ./src
RUN mvn -q clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /build/target/ims-0.0.1-jar-with-dependencies.jar app.jar
COPY src/main/resources/sql-schema.sql src/main/resources/sql-data.sql ./src/main/resources/
CMD ["java", "-jar", "app.jar"]