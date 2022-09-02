FROM maven:3.8.6-jdk-11 as MAVEN_BUILD

ADD ./pom.xml pom.xml
ADD ./src src/

RUN mvn clean package -DskipTests

FROM openjdk:11

WORKDIR /opt/workdir/
#.crt file in the same folder as your Dockerfile
ARG CERT="kfc.cer"
#import cert into java
COPY $CERT /opt/workdir/

RUN keytool -importcert -file $CERT -alias $CERT -cacerts -storepass changeit -noprompt

COPY --from=MAVEN_BUILD /target/telegrambot.jar /telegrambot.jar

ENTRYPOINT ["java","-jar","telegrambot.jar"]