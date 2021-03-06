# Felis Catus :cat:

Simple User's CRUD using Java Swing and PostgreSQL as DB.

## How to build

Run the __`build.bat`__ file for _Windows_ or __`build.sh`__ for _Linux_, both are in the root directory of this project

> After execution, the file __`felis-catus.jar`__ will be generated in the directory `target`

### How to up DB

```bash
docker-compose -f docker-compose.yml up -d
```

### Basic structure in DB

To create tables and schemas in the DB, run following command:

```bash
mvn compile flyway:migrate
```

To populate the database run the following file `src/main/resources/db/migration/V2__Add_users.sql`

----

## Goal

Create an example project with a visual interface for registering, querying, changing, and deleting user data

### Screenshot

----

![screenshot](img/screenshot.png)
