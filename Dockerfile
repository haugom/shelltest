FROM eclipse-temurin:17-jre-alpine
ENV JAVA_HOME=/opt/java/openjdk
RUN mkdir /opt/app
COPY target/demo-0.0.1-SNAPSHOT.jar /opt/app/app.jar
CMD ["java", "-jar", "/opt/app/app.jar"]
