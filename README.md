# Summary

This is a Java Spring project template for deployment to Heroku's free tier, which only supports Postgres databases.
The project includes:
* Spring MVC for web
* basic security - login required for all but the login/register pages
* Thymeleaf templating
* CircleCI

# Database

Use Postgres 9.5 or newer (10.x preferred, as that's Heroku's default).
(Note: Postgres and PostgreSQL are the same thing. Postgres is quicker to write, so that's that I'm using here.)

Use UTF8 as the instance collation.
Create a database with the required collation, e.g. for Hungarian:

```SQL
CREATE DATABASE dbname
    LC_COLLATE 'hu-HU-x-icu'
    LC_CTYPE 'hu-HU-x-icu'
    TEMPLATE template0;
```

You cannot change a database collation later, because it would break indexes (at least).
You must use template0, it's a Postgres thing. (Other templates already have set collations which interfere with database creation.)

# Environment variables

## Local

On Windows, you can run this in the command line to set env variables:

```
setx TEMPLATE_APP_PROFILE "dev-local"
setx TEMPLATE_APP_DB_URL "jdbc:postgresql://localhost:5432/hackathon"
setx TEMPLATE_APP_DB_USER "**TODO**"
setx TEMPLATE_APP_DB_USER_PASSWORD "**TODO**"
setx TEMPLATE_APP_HIBERNATE_DIALECT "org.hibernate.dialect.PostgreSQL95Dialect"
```

[Official docs for Postgres JDBC connection strings](https://jdbc.postgresql.org/documentation/head/connect.html)

## Heroku

On Heroku, set the environment variable:
TEMPLATE_APP_PROFILE = "dev-heroku"

This will trigger the use of the appropriate application.properties profile.

Heroku provides some magic environment variables automatically, whenever a Postgres database service is added to a Heroku environment. These are not listed in Heroku's env variable list, but they exist nonetheless. You **must** use these, rather than the physical db connection props, as those may be changed by Heroku without notice. 


# CircleCI

