FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/*.jar joblisting.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","joblisting.jar"]