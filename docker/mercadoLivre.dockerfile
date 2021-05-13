FROM openjdk
COPY target/mercadoLivre-0.0.1-SNAPSHOT.jar /var/www/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/var/www/mercadoLivre-0.0.1-SNAPSHOT.jar"]