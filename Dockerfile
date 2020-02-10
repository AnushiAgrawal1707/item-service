FROM openjdk:8-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 7070 available to the world outside this container
EXPOSE 7070

# The application's jar file
ARG JAR_FILE=target/itemservice-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} itemservice.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/itemservice.jar"]
