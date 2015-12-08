package edu.cmu.cs.cs214.chat;

import javax.swing.JFrame;

import edu.cmu.cs.cs214.chat.client.ChatClient;
import edu.cmu.cs.cs214.chat.client.ChatClientImpl;
import edu.cmu.cs.cs214.chat.gui.ClientPanel;

/**
 * Main class for Recitation 15.
 * 
 * @author tsun
 *
 */
public class Main {

    /**
     * Runs the chat client gui
     * 
     * @param args
     *            Command line arguments
     */
    public static void main(String[] args) {
        // create new ChatClient instance
        ChatClient client = new ChatClientImpl();
        // create new frame
        JFrame frame = new JFrame("Recitation 15");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // create new gui panel
        ClientPanel clientPanel = new ClientPanel(client);
        frame.setContentPane(clientPanel);
        // display the window.
        frame.pack();
        frame.setVisible(true);

    }
}
