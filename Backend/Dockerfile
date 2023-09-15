FROM openjdk:17
ENV JAVA_HOME /usr/bin/java
ARG JAR_FILE=Backend/build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]