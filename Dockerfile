FROM openjdk:8

COPY target/ebs-0.0.1-SNAPSHOT.jar app.jar

CMD ["java","-jar","app.jar"]