FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY instant-0.1.jar app.jar
EXPOSE 7788
CMD ["java", "-jar",  "-Dserver.port=7788", "-Dspring.profiles.active=dev" , "app.jar"]