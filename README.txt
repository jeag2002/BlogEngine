Java project created in Eclipse Mars 2 Release (4.5.2)

Java jdk1.8.0_101 (64 bits)
Maven 3.3
JUnit 4.9
Spring Boot 1.5.4
Spring Basic Security
Spring Cache.
SQLite 3.7.2

1 Only Project: SecureSymbio2Server.

Architechture of the project

REST services with two functionalities:

VIEW LAYER:

Management of POSTS

GET /postService/getPostById/** ==> Get post by ID (OPEN)
GET /postService/getPostsListByCriteria/** ==> Get post by Criteria (OPEN)
POST /postService/insertPost ==> create a post (Authenticated)
PUT /postService/updatePost/** ==> update a post by id and userid (Authenticated)
DELETE /postService/deletePost/** ==> delete a post by id and userid (Authenticated)
GET /postService/getPostByUID/** ==> get a list of post by userid (Authenticated)

(if you want to play with them, the user/password by default is user1/user1)

Management of USERS

POST /userService/insertUser ==> insert a user (OPEN)
PUT /userService/modifyUser/** ==> modify a user by userid (Authenticated)
GET /userService/getUserByUID/** ==> get all the data of the user, by userid (Authenticated)

More information here: (after launch the springboot engine: 
http://localhost:8080/swagger-ui.html (OPEN))

BUSINESS LAYER:

Declared as cacheable; the functionalities getPostById and getPostsListByCriteria.
When there is an update/delete/insert of a post; all the cache are deleted.
After 5 minutes all the cache are deleted.

Declare authentication as basic authentication (user:password) without encryption.
get the data of users, passwords and roles from user table.
Spring Security manages login mechanism. 


INTEGRATION LAYER:

All the data,tables and information are stored in a SQLite mydb.db file.

Table description:

CREATE TABLE User(
ID INTEGER PRIMARY KEY AUTOINCREMENT,  //id Table user
NAME CHAR(50),			       //name user		
ROLE CHAR(50),                         //role user (basic authentication)
EMAIL CHAR(50),			       //email user
PASSWORD CHAR(50),		       //password user	
ENABLED INT); 			       //enabled for authentication
		
CREATE TABLE Posts (			
ID INTEGER PRIMARY KEY AUTOINCREMENT,	//id Table Post
TITLE CHAR(30),				//Title of the Post
CONTENT CHAR(150),			//Content of the Post
USERID INTEGER,				//Id user creator of the Post
DATE CHAR(22),				//date creation/modification of the Post
FOREIGN KEY (USERID) REFERENCES USER(ID)//foreign relations between Post and User
)

//////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////
QUESTIONS

What HTTP verbs will be used for each endpoint? FOUR GET,POST,PUT,DELETE

What HTTP codes will be used for each response? 
200 if are all OK
401 Unauthorized
403 Forbidden
404 Not Found

How is the payload and the response formatted? 

Payload in Base64. Response it's a HTTP response. Depend on the functionality returns a JSON response
or a simple text.

Do you need to worry about HTTP headers in your requests?

No. In any case i am using http parameters, cookies, etc directly. Spring helps me to simplify this process

How is the error handling done?

As a HTTP Response message.

How would you document the API for a developer that is planning to use your service?

Using Swagger (Spring helps me in this step, too)
//////////////////////////////////////////////////////////////////////////////////