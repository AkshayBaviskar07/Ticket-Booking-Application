FROM openjdk:23-oraclelinux8
ADD target/Bookit-io.jar Bookit-io.jar
CMD ["java", "-jar", "Bookit-io.jar"]
EXPOSE 8080

