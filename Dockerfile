FROM java:8

RUN apt-get update
RUN apt-get install maven -y

WORKDIR ./users-services

ENV PORT 4567

ADD pom.xml /users-services/pom.xml
RUN ["mvn", "dependency:resolve"]

ADD src /users-services/src
RUN ["mvn", "clean"]
RUN ["mvn", "package"]

EXPOSE 4567
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "target/users-services-1.0-SNAPSHOT.jar"]

