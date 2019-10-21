FROM openjdk:8u212-jre-slim-stretch

ADD target/service.jar /opt/service.jar

#Definicion de un puerto que abre
EXPOSE 8080

ENTRYPOINT ["java", "-Djava.awt.headless=true","-Duser.timezone=America/Lima", "-Xms256m", "-Xmx256m", "-jar", "/opt/service.jar"]