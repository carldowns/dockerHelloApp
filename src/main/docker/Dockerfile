FROM openjdk:8-jre
WORKDIR /home/app
ADD ../target/TestApp.jar /home/app
ADD ../conf/conf.yaml /home/app
EXPOSE 8080 8081
CMD java -jar /home/app/TestApp.jar server /home/app/conf.yaml 
