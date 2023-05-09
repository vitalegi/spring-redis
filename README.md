# Spring Redis Playground

Playground app used to showcase the integration of a distributed cache with Spring Boot, covering local (windows), unit test and production-like situations.

## Prerequisites

- JDK 11
- Maven

```
$env:M2_HOME = 'C:\a\software\apache-maven-3.8.7-java11'
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-11.0.16.1'
$env:PATH = $env:M2_HOME + '\bin;' + $env:JAVA_HOME + '\bin;' + $env:PATH
```

## Compile

```bash
mvn clean package
```

## Run

### Local

```bash
$env:SPRING_PROFILES_ACTIVE='local'

# run compiled app
java -jar ./target/redis-0.0.1-SNAPSHOT.jar

# run from maven
./mvnw spring-boot:run
```

### Remote

```bash
$env:SPRING_PROFILES_ACTIVE='remote'

# run compiled app
java -jar ./target/redis-0.0.1-SNAPSHOT.jar

# run from maven
./mvnw spring-boot:run
```

