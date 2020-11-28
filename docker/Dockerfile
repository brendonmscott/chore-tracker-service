# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

MAINTAINER brendonmscott@gmail.com

ADD target/chore-tracker-service-1.0.0-SNAPSHOT.jar /opt/spring-cloud/lib/chore-tracker-service.jar

# For Debugging
#ENTRYPOINT ["/usr/bin/java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"]
ENTRYPOINT ["/usr/bin/java"]

CMD ["-jar", "/opt/spring-cloud/lib/chore-tracker-service.jar"]
