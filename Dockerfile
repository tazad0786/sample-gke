FROM openjdk:11-jre-slim
COPY target/sample-gke-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]