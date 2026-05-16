FROM eclipse-temurin:21

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x gradlew

RUN ./gradlew dependencies

EXPOSE 8080

CMD ["./gradlew", "bootRun"]
