FROM openjdk:8-jre-alpine

EXPOSE 8080

RUN mkdir -p /usr/app 

WORKDIR /usr/app

COPY ./target/java-maven-app-*.jar /usr/app

CMD java -jar java-maven-app-*.jar

# ENTRYPOINT ["java", "-jar", "java-maven-app-1.1.0-SNAPSHOT.jar"]