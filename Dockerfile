FROM java:8
EXPOSE 8080
ADD /build/libs/service-0.0.1-SNAPSHOT.jar service.jar
ENTRYPOINT ["java", "-jar", "service.jar"]