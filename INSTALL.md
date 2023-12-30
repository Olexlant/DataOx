## Prerequisites

Java 8 or later: Download and install from https://www.oracle.com/java/technologies/downloads/
Maven: Download and install from https://maven.apache.org/
PostgreSQL or MySQL database: Set up a local or remote database instance.
Google account (optional): If using the Google Sheets upload feature.
## Cloning the Repository

Clone the repository from GitHub:
Bash
git clone https://github.com/Olexlant/DataOx

## Database Setup

Create a database schema:

If using PostgreSQL, run the create_schema.sql script in the resources folder:
Bash
psql -U <username> -d <database_name> -f resources/create_schema.sql

If using MySQL, use the MySQL command-line tool or a GUI client to create the tables based on the schema in create_schema.sql.
Configure database connection:

Edit the application.properties file in the src/main/resources directory:
Properties
spring.datasource.url=jdbc:postgresql://localhost:5432/<database_name>
spring.datasource.username=<username>
spring.datasource.password=<password>
Replace placeholders with your database credentials.
## Building the Project

Run the Maven build command:
Bash
mvn clean install

## Running the Application

Start the Spring Boot application:
Bash
mvn spring-boot:run
Используйте код с осторожностью. Подробнее…
## Accessing the Application

Open your web browser and access the application at http://localhost:8080 (or the specified port).

## Troubleshooting

Ensure you have the correct dependencies installed.
Verify your database configuration.
Refer to the Spring Boot documentation for troubleshooting guidance.
