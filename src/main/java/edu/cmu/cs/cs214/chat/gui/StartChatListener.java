/**
 * 
 */
package edu.cmu.cs.cs214.chat.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import edu.cmu.cs.cs214.chat.client.ChatClient;
import edu.cmu.cs.cs214.chat.client.ClientChangeListener;

/**
 * @author nora
 *
 *         Listener that is used to start a chat session
 *
 */
public class StartChatListener implements ActionListener {

    private ChatClient mClient;
    private JTextField mName;
    private JTextField mIP;
    private JTextField mPort;
    private ClientChangeListener parent;


    /**
     * 
     * Listener for the start button that takes username, ChatServer port, and
     * ChatServer ip information and connects the current ChatClient to a
     * ChatServer
     * 
     * @param nameField
     *            field in which the username will be typed
     * @param portField
     *            field in which the port number of the server will be typed
     * @param ipField
     *            field in which the ip address of the server will be typed
     * @param client
     *            current ChatClient that is trying to connect to the server
     * @param gui
     *            ClientChangeListener that components belong to
     */
    public StartChatListener(JTextField nameField, JTextField portField,
            JTextField ipField, ChatClient client, ClientChangeListener gui) {
        mName = nameField;
        mClient = client;
        mIP = ipField;
        mPort = portField;
        parent = gui;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        mClient.setUsername(mName.getText());
        try {
            mClient.connectToServer(mIP.getText(),
                    Integer.parseInt(mPort.getText()));
        } catch (NumberFormatException e) {
            parent.displayErrorMessage("Invalid port/host combintation");
        }
    }
}
