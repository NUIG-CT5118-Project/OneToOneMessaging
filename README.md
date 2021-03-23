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
 
Message table
 
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
 
Database table has been updated:
 
CREATING USER WITH A REPEATED EMPLOYEE NUMBER:
Call using Postman:
 
Database has not been updated:
 
Error has been thrown:
 
### Get all users:

GET ALL USERS:
Call using Postman:
 
All users in database:
 
### Get a user by ID:
GETTING USER IN DATABASE:
Call using Postman:
 
The user with ID 1 in database:
 

GETTING USER NOT IN DATABASE:
Call using Postman:
 
User ID 9 not in database:
 
Error thrown:
 

### Create message with one sender and one recipient:

CREATE MESSAGE WITH REGISTERED, DIFFERENT USERS:
Database before:
 
Call using Postman:
 
Database after: 
 
CREATE MESSAGE WITH AN UNREGISTERED USER:
Call using Postman:
 
User ID 9 not in database:
 
Error thrown:
 
CREATE MESSAGE WITH SAME SENDER AND RECEIVER:
Call using Postman:
 
Error thrown:
 

### Edit a message:

EDIT MESSAGE IN DATABASE:
Call using Postman:
 
Update in database:
 

EDIT MESSAGE NOT IN DATABASE:
Call using Postman:

 
Error thrown:
 

EDIT MESSAGE SENDER OR RECEIVER:
Call using Postman:
 
Update in database:
 

### Delete A Message:

DELETE MESSAGE:
Call using Postman:
 
Database:
 

DELETE MESSAGE NOT IN DATABASE:
Call using Postman:
 
Database:
 
Error thrown:
 

### Get All Messages Between Two Users:

GET MESSAGES BETWEEN TWO USERS:
Call using Postman:
 
Database:
 

GET MESSAGES BETWEEN SAME USER:
Call using Postman:
 
Error thrown:
 

GET MESSAGES BETWEEN REGISTERED USER AND UNREGISTERED USER:
Call using Postman:
 
Error thrown:
 

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
