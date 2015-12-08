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
 *         Listener for anything that initiates the sending of a message.
 * 
 */
public class SendMessageListener implements ActionListener {

    private JTextField message;
    private ChatClient chatClient;
    private ClientChangeListener gui;

    private static final String CANNOT_SEND_MESSAGE = "Cannot send current message. Please try again.";


    /**
     * 
     * Listener used to indicate when a new message is being sent
     * 
     * @param messageField
     *            field in which the message will be typed
     * @param client
     *            current ChatClient
     * @param listener
     *            ClientChangeListener that components belong to
     */
    public SendMessageListener(JTextField messageField, ChatClient client,
            ClientChangeListener listener) {
        this.message = messageField;
        this.chatClient = client;
        this.gui = listener;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // attempt to send message
        if (chatClient.sendMessage(message.getText())) {
            System.out.println(message.getText());
            // if the message was sent successfully, clear out the field
            message.setText("");
            message.requestFocus();
        } else {
            gui.displayErrorMessage(CANNOT_SEND_MESSAGE);

        }

    }

}
