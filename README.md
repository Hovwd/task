# Spring Boot Webflux Reactive Mongo

This is a sample application that shows how to build a web application using
- Spring Boot 3
- Spring Webflux
- Spring Reactive Data MongoDb


<br/>

## Running

In application.yml, configure appropriate values.
<br/>
<br/>
Run this using using the gradle wrapper included

```
./gradlew bootRun
```

Run this using  docker

```
docker build -t task  .   
docker run -p 8080:8080 -e SPRING_PROFILE=dev docker.io/library/task   
```

And then go to http://localhost:8080 to test the API's.


## cURL Commands

Application has single URL

GET __/:color__

``` curl --location 'http://localhost:9292/photo/RED'```
