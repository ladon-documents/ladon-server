FROM openjdk:21
ENTRYPOINT ["java", "-jar", "/usr/share/ladon/ladon.jar"]
EXPOSE 8080
ARG JAR_FILE
ADD target/ladon.jar /usr/share/ladon/ladon.jar
