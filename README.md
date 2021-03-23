# Private Messaging Service

## Table of contents
-	Introduction
-	List of features
-	How to use
-	Examples of use
-	Technologies
-	Next version

## Introduction
A private messaging service for a company called Perry’s Summer Vacation Goods and Services. 
Company requires an API to be able to handle messages internally within the company.

## List of features
-	Create user
-	Get all users
-	Get user by ID
-	Create message with one sender and one recipient
-	Edit a message
-	Delete a message
-	Get all messages between two users

## How to install
-	Import as Maven project
-	Update the application.properties file spring.datasource.url to join to a local MySQL schema 
-	Run SpringbootMessageApplication.java file
-	Database tables will be created and populated 

## Explanation
-	Two tables, User and Message
-	User table holds records of all users and their associated attributes: user ID, user name and employee number (as a unique identifier of Perry’s employees to prevent duplicate user profiles being created – could be modified to be email/phone number, etc. once it is unique to the employee)
-	Message table holds records of all messages and their associated attributes: message ID, content of message, datetime message is sent, sender, receiver (sender and receiver cannot be the same)
User table

![User table](https://user-images.githubusercontent.com/72204705/112217762-cfe71b80-8c1a-11eb-8125-8bdbc59d7e19.png)

 
Message table

![Message table](https://user-images.githubusercontent.com/72204705/112217440-8c8cad00-8c1a-11eb-8408-f1b88c710eb5.png)


-	Create user: 
    -	POST method
    -	api/users
    -	user name and employee number are provided. User table is checked for an instance of employee number. If the employee number is not already associated with a user, a user is created. If the employee number is already associated with a user, an error is thrown. An employee should only have one user account. 
-	Get all users: 
    -	GET method
    -	api/users
    -	response is a list of all users in database.
-	Get user by ID: 
    -	GET method
    -	api/users/{userID}
    -	ID is provided. Response is the user with that ID.   
-	Create message with one sender and one recipient: 
    -	POST method
    -	api/messages
    -	message content, sender ID and receiver ID are provided. If the message is empty, if the sender or receiver are not registered users or sender and receiver are the same user, the message will not be created. 
-	Edit a message: 
    -	PUT method
    -	api/messages/{messageID}
    -	message content is provided. Message table is checked for an instance of message. If the message exists, only the message content is updated. If the message does not exist, an error is thrown. 
-	Delete a message: 
    -	DELETE method
    -	api/messages/{messageID}
    -	Message table is checked for an instance of message. If the message exists, only the message content is deleted. If the message does not exist, an error is thrown. 
-	Get all messages between two users: 
    -	GET method
    -	api/messages/findBySenderIDAndReceiverIDOrderByDateSentAsc/{senderID}/{receiverID}
    -	two user IDs are provided. Message table is checked for all messages where the two users are exchanging messages (i.e., where user1 is sender and user2 is receiver and where user2 is sender and user1 is receiver). 

## Examples of use with Postman

### Create user:

CREATING USER WITH UNIQUE EMPLOYEE NUMBER:
Call using Postman:

![PMcreateuser1](https://user-images.githubusercontent.com/72204705/112217868-ed1bea00-8c1a-11eb-8238-281f336c20da.png)


Database table has been updated:

![DBcreateuser1](https://user-images.githubusercontent.com/72204705/112217939-045ad780-8c1b-11eb-8ff5-c31d52c25a0f.png)

 
CREATING USER WITH A REPEATED EMPLOYEE NUMBER:
Call using Postman:

![PMcreateuser2](https://user-images.githubusercontent.com/72204705/112218002-18063e00-8c1b-11eb-9425-bcda2e15ff2d.png)

 
Database has not been updated:

![DBcreateuser2](https://user-images.githubusercontent.com/72204705/112218055-2ce2d180-8c1b-11eb-8f3d-94afae3e7029.png)

 
Error has been thrown:

![ERcreateuser2](https://user-images.githubusercontent.com/72204705/112218101-3e2bde00-8c1b-11eb-8f89-69679cb098e3.png)

 
### Get all users:

GET ALL USERS:
Call using Postman:

![PMgetallusers](https://user-images.githubusercontent.com/72204705/112218156-513eae00-8c1b-11eb-8261-e0c81d27735e.png)

 
All users in database:

![DBgetallusers](https://user-images.githubusercontent.com/72204705/112218233-67e50500-8c1b-11eb-8f19-15b19cd54cb8.png)

 
### Get a user by ID:
GETTING USER IN DATABASE:
Call using Postman:

![PMgetuser1](https://user-images.githubusercontent.com/72204705/112218313-7af7d500-8c1b-11eb-9492-7296c75677ae.png)

 
The user with ID 1 in database:

![DBgetuser1](https://user-images.githubusercontent.com/72204705/112218461-a37fcf00-8c1b-11eb-9248-f62c6b9b0e10.png)
 

GETTING USER NOT IN DATABASE:
Call using Postman:

![PMgetuser2](https://user-images.githubusercontent.com/72204705/112218520-b4c8db80-8c1b-11eb-8727-a869803389d8.png)

 
User ID 9 not in database:

![DBgetuser2](https://user-images.githubusercontent.com/72204705/112218563-c6aa7e80-8c1b-11eb-8255-4bf069ab581d.png)

 
Error thrown:

![ERgetuser2](https://user-images.githubusercontent.com/72204705/112218715-f9547700-8c1b-11eb-9029-9dbc7a17ccf8.png)
 

### Create message with one sender and one recipient:

CREATE MESSAGE WITH REGISTERED, DIFFERENT USERS:
Database before:

![DBcreatemessage1 1](https://user-images.githubusercontent.com/72204705/112218782-0e310a80-8c1c-11eb-932b-da710fb31ce1.png)

 
Call using Postman:

![PMcreatemessage1](https://user-images.githubusercontent.com/72204705/112218822-1c7f2680-8c1c-11eb-9d96-21fe88bb332f.png)

 
Database after: 

![DBcreatemessage1 2](https://user-images.githubusercontent.com/72204705/112218873-2dc83300-8c1c-11eb-96bd-67b957ccc1c4.png)

 
CREATE MESSAGE WITH AN UNREGISTERED USER:
Call using Postman:

![PMcreatemessage2](https://user-images.githubusercontent.com/72204705/112218925-40db0300-8c1c-11eb-9161-42df0e3b19e6.png)

 
User ID 9 not in database:

![DBcreatemessage2](https://user-images.githubusercontent.com/72204705/112219034-636d1c00-8c1c-11eb-9cfc-4fe9157b8eae.png)

 
Error thrown:

![ERcreatemessage2](https://user-images.githubusercontent.com/72204705/112219005-59e3b400-8c1c-11eb-92ab-99b1d7f8e393.png)

 
CREATE MESSAGE WITH SAME SENDER AND RECEIVER:
Call using Postman:

![PMcreatemessage3](https://user-images.githubusercontent.com/72204705/112219087-741d9200-8c1c-11eb-8917-2725a21ca1ea.png)

 
Error thrown:

![ERcreatemessage3](https://user-images.githubusercontent.com/72204705/112219120-813a8100-8c1c-11eb-8109-8cf665f9c891.png)
 

### Edit a message:

EDIT MESSAGE IN DATABASE:
Call using Postman:

![PMeditmessage1](https://user-images.githubusercontent.com/72204705/112219192-93b4ba80-8c1c-11eb-901b-f56557d19955.png)

 
Update in database:

![DBeditmessage1](https://user-images.githubusercontent.com/72204705/112219241-a3cc9a00-8c1c-11eb-9987-c9cbf371bda5.png)
 

EDIT MESSAGE NOT IN DATABASE:
Call using Postman:

![PMeditmessage2](https://user-images.githubusercontent.com/72204705/112219319-b9da5a80-8c1c-11eb-986b-2157d9a0af8a.png)

 
Error thrown:

![EReditmessage2](https://user-images.githubusercontent.com/72204705/112219352-c65eb300-8c1c-11eb-9e26-1cccdb3255c8.png)

 

EDIT MESSAGE SENDER OR RECEIVER:
Call using Postman:

![PMeditmessage3](https://user-images.githubusercontent.com/72204705/112219408-d8405600-8c1c-11eb-942c-9a7dcd257fa6.png)

 
Update in database:

![DBeditmessage3](https://user-images.githubusercontent.com/72204705/112219465-ed1ce980-8c1c-11eb-967c-deaabad07fc0.png)
 

### Delete A Message:

DELETE MESSAGE:
Call using Postman:

![PMdeletemessage1](https://user-images.githubusercontent.com/72204705/112219516-032aaa00-8c1d-11eb-881c-a0d535e25c2d.png)

 
Database:

![DBdeletemessage1](https://user-images.githubusercontent.com/72204705/112219586-1b9ac480-8c1d-11eb-9c6e-d69612d37f9b.png)
 

DELETE MESSAGE NOT IN DATABASE:
Call using Postman:

![PMdeletemessage2](https://user-images.githubusercontent.com/72204705/112219630-2d7c6780-8c1d-11eb-9498-3c20990bad70.png)

 
Error thrown:

![ERdeletemessage2](https://user-images.githubusercontent.com/72204705/112219721-4dac2680-8c1d-11eb-8296-c5f333298320.png)
 

### Get All Messages Between Two Users:

GET MESSAGES BETWEEN TWO USERS:
Call using Postman:

![PMgetmessages1](https://user-images.githubusercontent.com/72204705/112219782-60266000-8c1d-11eb-86b8-a9c47775929a.png)

 
Database:

![DBgetmessages1](https://user-images.githubusercontent.com/72204705/112219833-746a5d00-8c1d-11eb-9f75-d955ba9c0869.png)
 

GET MESSAGES BETWEEN SAME USER:
Call using Postman:

![PMgetmessages2](https://user-images.githubusercontent.com/72204705/112219925-9237c200-8c1d-11eb-8932-f32cba1059cd.png)

 
Error thrown:
 
![ERdeletemessage2](https://user-images.githubusercontent.com/72204705/112219981-a2e83800-8c1d-11eb-9db1-4f445aa626f1.png)


GET MESSAGES BETWEEN REGISTERED USER AND UNREGISTERED USER:
Call using Postman:

![PMgetmessages3](https://user-images.githubusercontent.com/72204705/112220038-b4314480-8c1d-11eb-9ee6-477e8a805537.png)

 
Error thrown:

![ERgetmessages3](https://user-images.githubusercontent.com/72204705/112220101-c6ab7e00-8c1d-11eb-9d1f-3c3b312b7ec6.png)
 

## Technologies
-	Java 11
-	Spring Boot
-	Spring Data JPA
-	Lombok
-	Apache Tomcat
-	Maven
-	MySQL 

## Next version
-	Adding additional table Chat and bridging table Chat-User.
-	Chat to User is many-to-many relationship (a user can belong to many chats and a chat can have many users).
-	Chat to Message is one-to-many (a chat can have many messages and a message can only belong to one chat).
-	This will allow for group chats. 
-	To get messages, API will be updated to api/chats/{chatID}/messages
-	To edit or delete a message, API will be updated to api/chats/{chatID}/messages/{messageID}
-	To get users of a chat, API will be updated to api/chats/{chatID}/users
-	To get a user’s chats, API will be api/users/{userID}/chats
