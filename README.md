"# FSD-JAVA-PIXOGRAM" 

# Project
# Microservices for pixogram
# 1.eureka-server
# 2.feed-service
# 3.media-service
# 4.user-service

#Angular8
#1. pixogram-ui

Run npm install
Run ng s


## Run Spring Boot application-  run each microservice in same above sequence order.
```
mvn spring-boot:run

#Local urls:
eureka-server : http://localhost:8761/
user-service  : http://localhost:8081/user-service
media-service : http://localhost:8082/media-service
feed-service  : http://localhost:8083/feed-service


Create Client credentials for S3 storage
update those credentials in media-service properties file


