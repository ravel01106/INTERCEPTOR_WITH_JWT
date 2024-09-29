# Example Project Interceptor + JWT

## Description
This project has as its purpose to show an example of how to use Spring interceptor for user authentication with JWT. In addition, this has not used Spring Security.

## Database in Docker

Inside resources package is the database MySQL configuration and the data to be imported into th database when you start the application, because this app deletes and creates the database when it is botted.

Moreover, inside docker-compose file is the way to mount the database and PHPMyAdmin app, you must be started it before running the application.