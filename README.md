# VehiclesAPI Project

This Project is done as part of Udacity Java developer course. The purpose of the project is to understand the concepts of Spring boot application, microservices, consuming services, mvc unit testing and documentation using swagger api. 

## Pre-requisites
 - Java v11.
 - Maven 3.6.1 - https://maven.apache.org/download.cgi

## Instructions

```
git clone https://github.com/rajashekar/vehicles-api-backend.git
```

Step 1: In one command line terminal and run Eureka server
```
cd eureka
mvn clean package
java -jar target/pricing-service-0.0.1-SNAPSHOT.jar
```

Step 2: Open another command line terminal and run boogle-maps service
```
cd boogle-maps
mvn clean package
java -jar target/boogle-maps-0.0.1-SNAPSHOT.jar
```

Step 3: Open another command line terminal and run pricing service 
```
cd ../pricing-service
mvn clean package
java -jar target/pricing-service-0.0.1-SNAPSHOT.jar
```

Step 4: Open another command line terminal and run Vehicle service
```
cd ../vehicles-api
mvn clean package
java -jar target/vehicles-api-0.0.1-SNAPSHOT.jar
```

Visit - http://localhost:8080/swagger-ui.html#/ to find the vehicle api documentation

## Examples
### CREATE vehicle
```
curl localhost:8080/cars \
    -H 'Content-type: application/json' \
    -d '{
        "condition": "NEW",
        "details": {
            "body": "sedan",
            "engine": "3.6L V6",
            "externalColor": "white",
            "fuelType": "Gasoline",
            "manufacturer": {
                "code": 101,
                "name": "Chevrolet"
            },
            "mileage": 32280,
            "model": "Impala",
            "modelYear": 2018,
            "numberOfDoors": 4,
            "productionYear": 2018
        },
        "location": {
            "lat": 40.73061,
            "lon": -73.935242
        }
    }'
```
### GET all vehicles
```
curl localhost:8080/cars
```
### GET vehicle by id
```
curl localhost:8080/cars/1
```
### Edit (PUT) vehicle by id
```
curl -X PUT localhost:8080/cars/1 \
    -H 'Content-type: application/json' \
    -d '{
        "condition": "NEW",
        "details": {
            "body": "sedan",
            "engine": "3.6L V12",
            "externalColor": "black",
            "fuelType": "Gasoline",
            "manufacturer": {
                "code": 101,
                "name": "Chevrolet"
            },
            "mileage": 32280,
            "model": "Impala",
            "modelYear": 2018,
            "numberOfDoors": 4,
            "productionYear": 2018
        },
        "location": {
            "lat": 40.73061,
            "lon": -73.935242
        }
    }'
```
### DELETE vehicle by id
```
curl -X DELETE localhost:8080/cars/1
```

For individual api documentation please refer below - 
- [Vehicles API](vehicles-api/README.md)
- [Pricing Service](pricing-service/README.md)
- [Boogle Maps](boogle-maps/README.md)

## Contributing
This repository is done as part of Udacity Java developer. Therefore, most likely will not accept any pull requests.
