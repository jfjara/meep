FROM openjdk:8-jdk-alpine
LABEL Juan Francisco Jara Bellido <juanfranciscojara@gmail.com>
COPY ./target/*.jar /home
ENTRYPOINT ["java","-jar", "/home/meep-test-1.0.jar"]