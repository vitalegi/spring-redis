# README

## Prerequisites

- JDK 11
- Maven

```
$env:M2_HOME = 'C:\a\software\apache-maven-3.8.7-java11'
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-11.0.16.1'
$env:PATH = $env:M2_HOME + '\bin;' + $env:JAVA_HOME + '\bin;' + $env:PATH
```

## Local

### Compile

```bash
mvn clean package
```

### Run

```bash
$env:SPRING_PROFILES_ACTIVE='local'

# run compiled app
java -jar ./target/cosucce-0.0.1-SNAPSHOT.jar

# run from maven
./mvnw spring-boot:run
```

