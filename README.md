# ChatServer
--------

This repository contains a small chat server implementation to be used for recitation. 

### Getting Started
------
##### Forking the Repository
In order to gain access to this repository, we would like you to **Fork** the repsoitory, and then clone your fork onto your local machine. More information about forking can be found [here](https://help.github.com/articles/fork-a-repo/).

##### Importing into Eclipse
Once you have successfully gained access to this repository, import the project into Eclipse. 

#####Running a Server

To run the server, run the `ChatServerImpl` class in the `edu.cmu.cs.cs214.chat.server` package. Once you run the server, your console should print out:
```
SERVER: Listening for incoming commands on port 15214.
```

#####Running a Client
To run the client, run the `Main` class in the `edu.cmu.cs.cs214.chat` package. Once you run the client, a Java application will open.

### Connecting to a Server
------
You will not be able to send messages until you connect to a chat server. 
In order to connect to a server, you will be prompted for the following information:

|Textbox    |Description                                                                          |
|:----------|:------------------------------------------------------------------------------------|
|Username   |The username you would like to use                                                   |
|Host Port  |The port that the chat server is listening on (e.g. 15214)                           |
|Host IP    |The IP address of the chat server or "localhost" if you plan to run things locally   |

Please note that in order to connect to the server, the server must be **running**.
