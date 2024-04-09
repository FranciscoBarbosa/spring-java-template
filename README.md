# Spring Java Template

![](https://img.shields.io/github/actions/workflow/status/franciscobarbosa/spring-java-template/build.yml)
![](https://github.com/FranciscoBarbosa/spring-java-template/blob/main/.github/badges/jacoco.svg)

This repository contains a template project with some of the best practices for developing a java application with Spring Boot. The use case is a simple **To Do** list applying all the techniques and tools specified below.

## Keywords: 

* Clean architecture
* CI/CD
* SAST tools
* DAST tools
* REST
* Reactive programming
* Non relational database
* Caching

## Development Environment

* Operational system: Linux
* Automation tool: [Taskfile](https://taskfile.dev/)
* IDE: [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* Code Version control: [Git](https://git-scm.com/)
* Container platform: [Docker](https://www.docker.com/)
* CI/CD platform: [Gitlab CI/CD](https://docs.gitlab.com/ee/ci/)

## Getting Started




## Tools, Patterns and Good Practices

### Language and Frameworks
* Java Version: [Java 17](https://jdk.java.net/17/)
* Spring version: [Spring Boot 3](https://spring.io/projects/spring-boot)
* Dependency management tool: [Maven](https://maven.apache.org/)
* Reactive toolkit: [Spring Webflux](https://docs.spring.io/spring-framework/reference/web/webflux.html)

### Tests
* Junit 5
* Unit test approach: [Sociable tests](https://martinfowler.com/bliki/UnitTest.html)
* [Testcontainers](https://testcontainers.com/) for integration testing
* Contract testing (makes sense only if I implement more than 1 service?) 
* Architecture unit testing library: [ArchUnit](https://www.archunit.org/)
* check if jqAssistant is useful (saw some metrics in Clean Architecture book that are measured in JQAssistant)

### Database
* Nrdbms: [mongoDB](https://www.mongodb.com/)
* migration tool: [Liquibase](https://www.liquibase.org/)

## Code quality

### Static Analysis Security Testing
* Source code static analysis: [SonarQube](https://www.sonarsource.com/)
* Source code known vulnerabilities scan: [Owasp top ten](https://owasp.org/www-project-top-ten/)
* Container vulnerabilities analysis: [Clair static analysis](https://github.com/quay/clair)

### Dynamic Analysis Security Testing
* [OWASP ZAP](https://www.zaproxy.org/)
*

### Security
* Sensitive data scan using pre-commit hooks: [Talisman](https://github.com/thoughtworks/talisman)
* Leaked credentials scanner: [TruffleHog](https://github.com/trufflesecurity/trufflehog)
* Container security layer: [AppArmor](https://apparmor.net/)


### CI (/CD ?)
* Pipeline: Gitlab 
* Project build
* Unit tests
* Integration test
* test coverage
* SAST & DAST


### Conventions
* Java code formatting:
* Git commit style: [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/)

### Best practices
* API-first strategy: REST API specification following [OpenApi](https://www.openapis.org/) format.
* REST API available via Swagger-ui only for development environment and not production.

## Version 0.0.1 Roadmap
* Create project structure based on Clean Architecture
* Add basic authentication to the API
* Add ArchUnit
* Add simple CRUD repository 
* Add Sociable Unit tests
* Add Integration tests
* Add test coverage 
* Add Liquibase

## Version 1.0.0
* Containerize application with [Docker](https://www.docker.com/)
* Add Talisman, TruffleHog and AppArmor
* Add OWASP top 10 scanner
* Create commit rule checker
* WebFlux

## Version 2.0.0
* Add test containers
* Add SonarQube
* Add local cache
* Add visual code evolution as DNA
* REST third level of maturity

## Version 3.0.0
* Configure Ci pipeline using [Gitlab](https://about.gitlab.com/)
* CLAIR static analysis
* DAST
* Auto-identify dependencies update
* Auto-identify version to increase (Maven plugin?)

## Future
* Monitoring
* Acceptance criteria tests
* Smoke tests
* Add option to run with GraalVm (compare metrics between Graalvm and Jvm)
* Feature Flag pattern

# Next Steps
* Async communication between 2 microservices using Kafka
* Implement Microservices best practices
* Implement RPC with Kafka
* Contract testing























