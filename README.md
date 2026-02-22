# Project Title 
Tick Sighting — a Minimum Viable Product (MVP) backend application that brings tick sighting data to life. It processes a provided dataset of tick sightings and exposes services for managing and analysing the data.

# Project Description 
The primary goal of this backend application is to ingest, store and manage tick sighting data, enabling searching, filtering and reporting. The system processes the raw dataset, persists valid records in a database and exposes RESTful API endpoints for data retrieval and analysis.

Each sighting is uniquely identified by an ID used as the primary key to prevent duplicates and ensure data integrity. The application supports filtering by time range and location, as well as generating aggregate insights such as regional counts and trends over time.

# Technologies used
- Frontend: HTML, CSS, JavaScript
- Backend: Java, SpringBoot
- Database: H2(file-based: ./ticksdb.mv.db)

# Project structure 
- back-end - Spring Boot application 
- .gitignore
- package.json
- README.md

# HOW TO RUN 
1. Navigate to project root: cd back-end/demo

<--Temporary fix: needed only if java is not configured on the system-->
$env:JAVA_HOME="C:\Program Files\Java\jdk-25"
$env:Path="$env:JAVA_HOME\bin;$env:Path"

2. Run the app using Maven: .\mvnw spring-boot:run
3. To access the API: http://localhost:8080

# H2 Console (lightweight java DB): 
1. Open in browser using link: http://localhost:8080/h2
2. Use the URL: jdbc:h2:file:./ticksdb
3. Username -> sa
   Password -> 

# API tests 
- GET http://localhost:8080/sightings
- GET http://localhost:8080/sightings?location=London
- GET http://localhost:8080/reports/regions
- GET http://localhost:8080/health

