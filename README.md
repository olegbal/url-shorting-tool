# Url-Shorting-Tool

Url shorter written with using angular-2 framework(on client side) and spring framework(on server side)

# Important info 

Backend has feature that removes expired links. I set expiring period on 60 seconds(for demonstrating only).
To change expiring period see com.github.olegbal.urlshortingtool.service.impl.ExpiredLinksServiceImpl class

# Prerequisites
Make sure that you have inststalled 

```
* nodejs v6.9.5 and higher.
* jdk 8
* maven 
```

## Installing

```
cd ./client
npm install
```
# Running

### Client 

To run client use
```
cd ./client
npm start 
```
Client port 4200

### Server

To run server

```
cd ./server
mvn spring-boot:run
```

Server  port 8080

# Testing

```
mvn test
```

## Server api

### Auth
* POST /api/v1/login -log in system.
* POST /api/v1/register - register with role user/admin. 

### Links

* GET  /api/v1/links?page=0&size=5 -- getting paginated links
* GET  /api/v1/links?tag=tagname&page=0&size=5 -- get paged links by tag
* GET  /api/v1/links/{id} -- get link info by id
* GET  /api/v1/links?userId=1&page=0&size=4 -- get all paged user's links
* GET /api/v1/links/check?url="adress" -- check is url exists in db
* POST /api/v1/links?userId=1 -- create link
* PUT  /api/v1/links?userId=1 -- update user's link
* DELETE /api/v1/links/{id} remove link by id

### Shortedlinks
* GET /api/v1/shortlinks/{linkValue} find original link in db and redirect to it (by shorted link)

### Users
* GET /api/v1/account?userName="login" -- get user account details (logn + links)

* GET /api/v1/users -- get all registered users with role USER (will be in admin cabinet)
* DELETE /api/v1/user/{id} --remove user by id

### Production mode

To make unified jar file use

```
cd ./client
ng build
cd ../server
mvn clean install
```

Then to run from IDE

```
mvn spring-boot:run
```
