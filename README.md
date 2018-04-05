# Synopsis

This project is created to demo REST api's for hospital data as a micro-service.


# Environment setup

### Install Java
* Make sure that java executables are in the PATH. This project requires Java 8.

### Install Maven
* Make sure that maven executables are in the PATH.
* Make sure that settings.xml is properly configured with proxy if any.

### Install Git

##### Git global setup

 git config --global user.name "firstName lastName"

 git config --global user.email "user@domain.com"


##### Clone the repository
git clone https://github.com/mjspka/dell-interview-hospital-data.git
cd dell-interview-hospital-data

# Run the application
* cd dell-interview-hospital-data
* mvn package && java -jar target/dell-interview-hospital-data-1.0.jar

# API Reference
### Prerequisites
* Start the application either locally on your laptop/desktop or in a remote server.
### How to access the api?
If you are running the application locally. Please refer the API from the [URL](http://localhost:8080/swagger-ui.html). 

# Features
### Swagger
Swagger is added to the project. All the api's that are part of this micro-service can be viewed from URL, <server-ip/host-name>:8080/swagger-ui.html

###Cache
Cache is added to the rest api. FYI: The cache will be automatically evicted every 1 minute.

###jUnits
All the code is covered with jUnits

# Contributors

Praveen Malla

# License

TBD

# How to make changes to this file?
[Click me to learn about markup](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)
