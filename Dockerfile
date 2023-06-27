FROM openjdk:17-jdk-alpine
COPY target/DriverLookupSystem-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "/DriverLookupSystem-0.0.1-SNAPSHOT.jar"]

