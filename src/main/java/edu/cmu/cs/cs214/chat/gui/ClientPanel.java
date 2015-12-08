/**
 * 
 */
package edu.cmu.cs.cs214.chat.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import edu.cmu.cs.cs214.chat.client.ChatClient;
import edu.cmu.cs.cs214.chat.client.ClientChangeListener;
import edu.cmu.cs.cs214.chat.server.Message;

/**
 * ClientPanel a GUI for the ChatClient interface
 * 
 * @author nora
 *
 */
public class ClientPanel extends JPanel implements ClientChangeListener {
    private static final int FIELD_WIDTH = Integer.parseInt("60");
    private static final int INFO_WIDTH = Integer.parseInt("20");
    private static final int AREA_WIDTH = FIELD_WIDTH + Integer.parseInt("10");

    private static final String USERNAME_TEXT = "Username: ";
    private static final String PORT_TEXT = "Host Port: ";
    private static final String IP_TEXT = "Host IP: ";
    private static final String OK = "OK";
    private static final String ERROR_ENCOUNTERED = "Error";

    private static final int AREA_HEIGHT = Integer.parseInt("20");

    private ChatClient client;

    private final JLabel usernameLabel;
    private final JLabel portLabel;
    private final JLabel ipLabel;

    private final JTextField usernameField;
    private final JTextField portField;
    private final JTextField ipField;
    private final JTextField messageField;

    private final JScrollPane scrollPane;
    private final JTextArea chatArea;

    private final JButton startButton;
    private final JButton sendButton;


    /**
     * Constructor for ClientPanel takes in an instance of the ChatClient that
     * it will be representing.
     * 
     * @param chatClient
     *            ChatClient the gui will be representing
     */
    public ClientPanel(ChatClient chatClient) {
        this.client = chatClient;
        chatClient.addClientChangeListener(this);

        usernameLabel = new JLabel(USERNAME_TEXT);
        portLabel = new JLabel(PORT_TEXT);
        ipLabel = new JLabel(IP_TEXT);

        usernameField = new JTextField(FIELD_WIDTH);
        portField = new JTextField(FIELD_WIDTH);
        ipField = new JTextField(FIELD_WIDTH);
        messageField = new JTextField(FIELD_WIDTH);

        chatArea = new JTextArea(AREA_HEIGHT, AREA_WIDTH);
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(chatArea);
        this.scrollPane.getViewport().setAutoscrolls(true);

        startButton = new JButton("Start");
        sendButton = new JButton("Send");

        this.setLayout(new BorderLayout());
        this.add(createStartPanel(), BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(createSendPanel(), BorderLayout.SOUTH);

        this.messageField.setEnabled(false);
        scrollPane.setEnabled(false);
        this.sendButton.setEnabled(false);

        sendButton.addActionListener(new SendMessageListener(messageField,
                client, this));
        startButton.addActionListener(new StartChatListener(usernameField,
                portField, ipField, client, this));
    }


    private JPanel createStartPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(createUserInfoPanel());
        panel.add(startButton);

        return panel;
    }


    private JPanel createUserInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        namePanel.add(this.usernameLabel);
        namePanel.add(this.usernameField);
        panel.add(namePanel);

        JPanel ipPanel = new JPanel();
        ipPanel.setLayout(new FlowLayout());
        ipPanel.add(this.ipLabel);
        ipPanel.add(this.ipField);
        panel.add(ipPanel);

        JPanel portPanel = new JPanel();
        portPanel.setLayout(new FlowLayout());
        portPanel.add(this.portLabel);
        portPanel.add(this.portField);
        panel.add(portPanel);

        return panel;
    }


    private JPanel createSendPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.messageField, BorderLayout.CENTER);
        panel.add(this.sendButton, BorderLayout.EAST);
        messageField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // Ignore
            }


            @Override
            public void keyReleased(KeyEvent e) {
                // Ignore
            }


            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendButton.doClick();
                }
            }
        });
        return panel;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.cmu.cs.cs214.rec15.gui.ClientChangeListener#startChat(java.lang.String
     * , java.lang.String, java.lang.String)
     */
    @Override
    public void startChat(String username, String port, String ip) {
        this.messageField.setEnabled(true);
        this.scrollPane.setEnabled(true);
        this.sendButton.setEnabled(true);
        this.messageField.requestFocus();
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.cmu.cs.cs214.rec15.gui.ClientChangeListener#messageReceived(java.
     * lang.String)
     */
    @Override
    public void messageReceived(Message msg) {

        // TODO: Make the server show the timestamp of the received message.
        // Probably should use DateFormat (SimpleDateFormat) to format the date.
        // Date#getMinute, Date#getHour etc are deprecated in favor of this
        // method

        String newText = String.format(" %s: %s%n", msg.getSender(),
                msg.getContent());
        this.chatArea.append(newText);
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }


    /**
     * Displays a pop-up error message
     * 
     * @param message
     *            text of message to be displayed
     */
    @Override
    public void displayErrorMessage(String message) {
        JFrame frame = (JFrame) SwingUtilities.getRoot(this);

        Object[] options = { OK };

        JOptionPane.showOptionDialog(frame, message, ERROR_ENCOUNTERED,
                JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                options, options[0]);
    }

}