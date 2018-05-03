# Climbstats Server
This is the backend of **Climbstats**, an application to monitor progress in
climbing skills. It is composed of a server and REST API developed in Clojure
on top of an H2 database.

The app is available here: https://github.com/vincent-heng/climbstats-app

## Run

Before starting the server for the first time, run the following command to
initiate the database:

```
lein migrate
```

Start the web server:

```
lein ring server
```

The API documentation is then accessible at
[http://localhost:8080/swagger](http://localhost:8080/swagger)

## Database
When run locally, the application is in *dev* mode. The database is located in
the *resources/dev/db* folder.

Climbstats is using the database H2:
* on file in dev mode
* in memory when running tests


## Test
Run the tests:

```
lein test
```


## Deploy

* Put desired production configuration in *resources/prod*
* Update the *uberjar* profile configuration in *project.clj* if need be

Package the application for production:

```
lein ring uberjar
```

### Heroku
For deploying on Heroku:
* Overload Heroku's default build command by creating a *build* script in a
  *bin* subdirectory:

```
  #!/bin/bash
  lein with-profile uberjar migrate
  lein ring uberjar
```

* Overload Heroku's default run command by creating a file named *Procfile*:

```
web: java -jar target/climbstats-0.1.0-SNAPSHOT-standalone.jar
```

## Team

- [Vincent Heng](https://github.com/vincent-heng): Android App Developer
- [Guillaume Hélouis](https://github.com/ghelouis): REST Server Developer
