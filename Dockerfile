FROM eclipse-temurin:25-jdk-jammy
#VOLUME /tmp


ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 80
#docker build -t sample-api:v1.0 .
#docker run --name sample-api -d -p 8091:8091 sample-api:v1.0
#docker run --name sample-api -d -p 8091:8091 -v /c/samplevolume:/test sample-api:v1.0


