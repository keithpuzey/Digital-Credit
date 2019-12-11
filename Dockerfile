FROM java:8
EXPOSE 3500
COPY /target/digitalcredit-1.0.1.104.jar /opt/digitalcredit/
COPY /target/classes/application.properties /opt/digitalcredit/digitalcredit.properties
WORKDIR /opt/digitalcredit
CMD ["java", "-jar", "digitalcredit-1.0.1.104.jar", "--spring.config.location=digitalcredit.properties"]