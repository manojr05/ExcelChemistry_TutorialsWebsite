FROM openjdk:17
EXPOSE 8080
ADD target/excel_chemistry.jar excel_chemistry.jar
ENTRYPOINT ["java", "-jar", "/excel_chemistry.jar"]