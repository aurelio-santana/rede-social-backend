FROM eclipse-temurin:17-jdk-alpine
ADD target/socialNetwork-*.jar socialNetwork.jar
ENTRYPOINT ["java", "-jar", "/socialNetwork.jar"]