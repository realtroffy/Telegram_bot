FROM maven:3.8.6-jdk-11 as MAVEN_BUILD

ADD ./pom.xml pom.xml
ADD ./src src/

RUN mvn clean package -DskipTests

FROM openjdk:11

COPY --from=MAVEN_BUILD /target/telegrambot.jar /telegrambot.jar

ENTRYPOINT ["java","-jar","telegrambot.jar"]