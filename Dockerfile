FROM maven:3.8.6-jdk-11 as MAVEN_BUILD

ADD ./pom.xml pom.xml
ADD ./src src/

RUN mvn clean package

FROM openjdk:11

COPY --from=MAVEN_BUILD /target/demo-0.0.1-SNAPSHOT.jar /demo.jar

ENTRYPOINT ["java","-jar","demo.jar"]