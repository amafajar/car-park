# car-park
Project to find available car park in SG

This project using Hexagonal Architecture

Benefit
- **Isolate** the application and business logic from external factors
- Helps implement the Domain-Driven Design by making sure that the domain logic does not leak out of the core
- The ports and adapters are just as replaceable as all the external entities, further contributing to the **scalability** of the entire application
- The advanced separation of concerns also makes the app easier to **maintain**

More info - https://blog.octo.com/hexagonal-architecture-three-principles-and-an-implementation-example/

How to Start the Application
-

1. This project using **java 21** and **Spring Boot Version 3.1.5**
2. This project using **Spring Webflux** framework https://docs.spring.io/spring-framework/reference/web/webflux.html
3. Currently using PostgreSQL as database. Init schema using *flyway* https://flywaydb.org/. DDL can be seen on resources/db/migration
4. Use endpoint {**baseURL**}/car-park/v1/health-check to check if the app running correctly

External Parties
-
1. Onemap - https://www.onemap.gov.sg/
2. Data Govt Sg - https://beta.data.gov.sg/

Main feature
-
1. Convert CSV Data and insert on db - POST {**baseURL**}/car-park/v1/information
2. Reverse Geo Code, convert SVY21 format coordinate to WGS84 format - GET {**baseURL**}/car-park/v1/information/geocode
3. Fetch Availability - GET {**baseURL**}/car-park/v1/availability
4. Find the Nearest Available Car Park using coordinate - GET {**baseURL**}/car-park/v1/car-park/nearest?latitude=1.368768388&longitude=103.8502&page=1&per_page=10

Approach and Trade-off
- 

- Onemap has rate limit 250 API Calls per minute. Populate data to db and converting geo coordinate need to split up
- Some coordinate given by CSV file not really accurate -> add buffer *(in meters) to rounds up all buildings in a circumference from a point and search building
- Onemap API call need to be authenticated. Use ExchangeFilterFunction to auth API Call to auth once for any subsequent API Call
- Need to buffer WebClient for Govt Sg API Availability because the large data response
- Govt Sg API has no proper rate limit response, it will just return 400 Bad Request. Add retry with interval to call the API
- Calculate distance with coordinate implemented using Haversine Formula - https://en.wikipedia.org/wiki/Haversine_formula
- Use WebFlux caching mechanism to store Car Park Info and Availability - improve speed while maintain some degree of freshness

Things to Improve
-

- Enrich exception handling
- Add more comprehensive unit test
- Fine tune and optimize API response time
- Add mechanism to keep availability updated

Change Logs
-

- Change Car Park Information call using Map. Improve response time from 200-300ms to <100ms