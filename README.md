# Spring boot starter kit
An opinionated kit to start hacking in SpringBoot

# What is included?

- a `gradle` configuration with separate modules
  - `dto`: Data tranfer objects
  - `persistense`: DB resources and Entities
  - `service`: Business logic
  - `web`: the Spring Boot application
- `H2` used as the temp in-memory db
- a sample flow to get/set a User entity and the consequent parts:
  - `UserRequestDto/UserResponseDto` in `dto` module
  - `UserDao/UserRepository` in `persistence` module
  - `UserConverter\UserService\UserUtils\UserValidator` in `service` module
  - `UserController` in `web` module

# Gradle tasks

```
cd application
./gradlew clean build
./gradlew test
./gradlew codeCoverage
SPRING_PROFILES_ACTIVE=development ./gradlew sbsk-web:bootRun
```

## Verify running application

- to check a simple endpoint open http://localhost:8080/api/v1/user/get?name=Your name
- to chech all active endpoints open http://localhost:8080/swagger-ui.html#/operation-handler
- to check the api you can also use the postman collections in `documentation/api/postman`
