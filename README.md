# Spring Boot back-end for Vaadin CRM

## What you will need
1. An Integrated Developer Environment (IDE)
    - Popular choices include [IntelliJ IDEA](https://www.jetbrains.com/idea/), [Spring Tools](https://spring.io/tools), [Visual Studio Code](https://code.visualstudio.com/docs/languages/java), or [Eclipse](https://www.eclipse.org/downloads/packages/), and many more.
2. A Java Development Kit (JDK)
    - We recommend [AdoptOpenJDK](https://adoptopenjdk.net/) version 11
## Run the project
  - [Download](https://github.com/vaadin/crm-react-backend/archive/develop.zip) and unzip the source repository, or clone it using `git clone https://github.com/vaadin/crm-react-backend.git`
  - cd crm-react-backend
  - run
    - `./mvnw spring-boot:run` for MacOS/Linux
    - `mvnw spring-boot:run` for Windows
## Build a Docker image

1. build the app for production: `mvn package`
1. create a docker image: `docker build -t crm-react-api .`
1. run the docker image: `docker run -it --rm -p 8082:8082 crm-react-api`
1. try making API calls with cURL:
   - log in
     ```bash
     curl -v -X POST http://localhost:8082/login \
       -H "Content-Type: application/json" \
       -d '{"name": "user", "password": "password"}'
     ```
     Check that the response includes an `Authorization` HTTP header, e.g. `Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjA4ODk5NzE0fQ.9JpyrCDU7M366lSko7Ibe_P8c_S9xvLelqwYgTeHhE6rgYj53UkxL_3Eyr3e2J3lPnDrS14GWEVN3-L6ANu-9Q`
   - get the list of contacts
     ```bash
     curl http://localhost:8082/contacts \
       -H "Authorization: Bearer [token]"
     ```
     Check that the response is a JSON-encoded array of contacts.