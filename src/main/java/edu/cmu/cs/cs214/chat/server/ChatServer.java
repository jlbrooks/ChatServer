package edu.cmu.cs.cs214.chat.server;

import java.util.ArrayList;

/**
 * Interface for chat servers
 * 
 * @author tsun
 *
 */
public interface ChatServer {

    /**
     * Gets the number of clients connected to the server
     * 
     * @return number of clients currently connected to the server
     */
    int getNumClients();


    /**
     * Gets the message log of the server
     * 
     * @return message log of the server
     */
    ArrayList<Message> getMessages();


    /**
     * Sets the port of the ChatServer
     * 
     * @param port
     *            Port to listen on
     */
    void setPort(int port);


    /**
     * Starts the master server
     */
    void startServer();


    /**
     * Stops the server
     */
    void stopServer();

}
