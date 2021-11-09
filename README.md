# Round-Up Task
Elliott Davies
## Pre-requisites
- Java 11+
- Valid Token for sandbox customer from https://developer.starlingbank.com/login
- Token populated in application.properties - accessToken property
- Transactions populated on the account

## Compile and package the app

Within your IDE, using the Maven plugin, execute the Maven **_clean_** and **_package_** shortcuts

Alternatively,

In a terminal, run the `mvn clean install` command, followed by `mvn clean package`

## Running the app:
Within your IDE, navigate to _src/main/java/com/starling/roundup/RoundupApplication.java_ and run/debug the class.

Alternatively,

In a terminal, navigate to the generated `/target` folder and execute the following command: `java -jar roundup-0.0.1-SNAPSHOT.jar`

## Endpoints:
* `GET` http://localhost:8080/round-up - performs the round-up operation
*  See  _src/main/java/com/starling/roundup/controller/ManualTestController.java_ for alternative endpoints to manually test individual stages of the round-up process

## Considerations/Scope for further work
- The endpoint URLs should be stored in the applications.properties file rather than hard coded in the services.
- Develop tests rather than utilise a manual-test controller to give confidence in components' respective functionalities
- In hindsight, the `{Domain}Response` DTO naming convention should be refactored to `{Domain}Wrapper` - this better reflects the use of these classes.
- Logging and exception handling should be incorporated.
- RestTemplate .exchange calls should be wrapped in try-catches.
- Consumption of APIs is typically easier with non-statically typed languages/frameworks. I wanted to test myself by attempting to implement the solution in Java/SB, however I acknowledge that this would likely have been an easier (and quicker) task had I opted for e.g React with axios 
- Mark POJOs/DTOs to ignore unknown properties `@JsonIgnoreProperties(ignoreUnknown = true)` and remove unused fields.

##Resources used
- https://developer.starlingbank.com/docs#api-reference-temp- 
- https://start.spring.io/
- https://stackoverflow.com/questions/36151421/could-not-autowire-fieldresttemplate-in-spring-boot-application
- https://javahowtos.com/guides/107-spring/363-how-to-add-headers-to-resttemplate-in-spring.html
- https://stackoverflow.com/questions/59929037/resttemplate-with-bearer-authorization
- https://stackoverflow.com/questions/22849897/converting-json-into-a-dto-array
- https://json2csharp.com/json-to-pojo
- https://stackoverflow.com/questions/30177787/how-to-round-a-bigdecimal-value-to-its-nearest-hundreths