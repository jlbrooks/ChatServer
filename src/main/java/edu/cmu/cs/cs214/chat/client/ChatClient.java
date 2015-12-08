package edu.cmu.cs.cs214.chat.client;

/**
 * Interface for chat clients
 * 
 * @author tsun
 *
 */
public interface ChatClient {

    /**
     * Sends the message the server it is connected to
     * 
     * @param message
     *            Message to be sent
     * @return True if the message was successfully sent, false otherwise
     */
    boolean sendMessage(String message);


    /**
     * Gets the username of the client
     * 
     * @return Username of the client
     */
    String getUsername();


    /**
     * Sets the username of the client
     * 
     * @param username
     *            Username of the client
     */
    void setUsername(String username);


    /**
     * Adds a new ClientChangeListener to the list of listeners
     * 
     * @param listener
     *            ClientChangeListener to be added
     */
    void addClientChangeListener(ClientChangeListener listener);


    /**
     * Removes a ClientChangeListner to the list of listeners
     * 
     * @param listener
     *            ClientChangeListener to be removed
     */
    void removeClientChangeListener(ClientChangeListener listener);


    /**
     * Connects to the server specified by the arguments
     * 
     * @param hostname
     *            Address of the ChatServer
     * @param port
     *            Port of the ChatServer
     */
    void connectToServer(String hostname, int port);


    /**
     * Determines if the ChatClient is connected to the ChatServer
     * 
     * @return True if the client is connected to server, false otherwise
     */
    boolean isConnected();
}
