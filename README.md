# Hello World: Hystrix

Simple app to demonstrate Hystrix's fallback. 

## Run

See main classes in `com.github.yclian.hello.hystrix`. Or you can use the Gradle wrapper to execute these separately:

```
./gradlew runServer &
./gradlew runProducer &
./gradlew runConsumer -PemployeeId=2
```
 
