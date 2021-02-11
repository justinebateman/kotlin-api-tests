# kotlin-api-tests
This repo holds API Integration tests for the Sample API here - [https://jsonplaceholder.typicode.com/](https://jsonplaceholder.typicode.com/)

Technologies used:
- Kotlin
- Spring Boot
- Gradle
- TestNG
- RestAssured
- Jackson 
- AssertJ 

## Running the tests

All Tests

```
./gradlew clean test
```

Smoke tests

```
./gradlew clean test -Dgroups=smoke
```

To run all tests but exclude a certain group you can run
```
./gradlew clean test -Dgroups= -DexcludedGroups=known-issues
```