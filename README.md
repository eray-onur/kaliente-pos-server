# Kaliente POS - Web API
### Instructions for Docker Compose
1. Build project
   `./mvnw package`
   *or* 
   (Skipping tests is recommended if database is coupled with API) 
   `./mvnw package -DskipTests`

2. Rebuild docker image
    `docker build --build-arg JAR_FILE=target/*.jar -t kalientepos/webapi:latest .`

3. Start up the docker compose.
    `docker-compose up`

### Instructors for manual booting

1. Build project
   `./mvnw package`
   *or* 
   (Skipping tests is recommended if database is coupled with API) 
   `./mvnw package-DskipTests`

2. `java -jar ./target/{%jar_name%}.jar`
   jar name should be the main jar within the target directory.


> After running the project once, run the SQL Script "2_populate_administration_data.sql" inside main/resources/db directory for the database. Script will seed the database with Superadmin and an Admin account.