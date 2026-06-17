FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN find src -name "*.java" > sources.txt && javac -d out @sources.txt

CMD ["java", "-cp", "out", "core.Main"]