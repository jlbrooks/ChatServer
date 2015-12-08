package edu.cmu.cs.cs214.chat.client;

import edu.cmu.cs.cs214.chat.server.Message;

/**
 * ClientChangeListener is a class meant to be implemented by any GUIs being
 * made for the ChatClient
 * 
 * @author nora
 *
 */
public interface ClientChangeListener {

    /**
     * Called when a user wants to enter the chat room
     * 
     * @param username
     *            of the user
     * @param port
     *            that the server is listening on
     * @param ip
     *            address of the user's computer
     */
    void startChat(String username, String port, String ip);


    /**
     * Called when a new message is received
     * 
     * @param message
     *            The new message received
     */
    void messageReceived(Message message);


    /**
     * Called when an error message needs to be displayed
     * 
     * @param message
     *            text of error message to display
     */
    void displayErrorMessage(String message);
}