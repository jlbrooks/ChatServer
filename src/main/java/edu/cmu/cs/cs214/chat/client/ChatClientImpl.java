package edu.cmu.cs.cs214.chat.client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.net.SocketException;

import edu.cmu.cs.cs214.chat.server.Message;
import edu.cmu.cs.cs214.chat.util.Log;

/**
 * Implementation of a chat client that connects to server using Sockets
 * 
 * @author tsun
 *
 */
public class ChatClientImpl extends Thread implements ChatClient {
    private static final String TAG = "CLIENT";
    private Socket socket = null;
    private String username;
    private ObjectOutputStream out;
    private List<ClientChangeListener> listeners = new ArrayList<ClientChangeListener>();


    @Override
    public void addClientChangeListener(ClientChangeListener listener) {
        listeners.add(listener);
    }


    @Override
    public void removeClientChangeListener(ClientChangeListener listener) {
        listeners.remove(listener);
    }


    @Override
    public boolean sendMessage(String message) {
        try {
            Message msg = new Message(message, username);
            out.writeObject(msg);
            return true;
        } catch (SocketException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                Log.e(TAG, "Unable to close socket.");
            }
            Log.e(TAG, "Server closed connection.");
        } catch (IOException e) {
            Log.e(TAG, "Unable to send message to server.");
        }
        return false;
    }


    @Override
    public void setUsername(String name) {
        this.username = name;
    }


    @Override
    public String getUsername() {
        return this.username;
    }


    @Override
    public void connectToServer(String host, int port) {
        // Terminate any existing connections
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                // Ignore the error
                Log.e(TAG, "Unable to terminate connection with previous host");
            }
        }
        // Connect to the new server
        try {
            Log.i(TAG, String.format("Connected to server %s:%d.", host, port));
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e.toString());
            return;
        } catch (IOException e) {
            Log.e(TAG, String.format("Could not connect to %s:%d", host, port));
            return;
        }

        this.start();
        notifyListenersConnectedToServer(host, port);
    }


    @Override
    public boolean isConnected() {
        return (socket != null) && !socket.isClosed();
    }


    @Override
    public void run() {
        try {
            while (true) {
                ObjectInputStream in = new ObjectInputStream(
                        socket.getInputStream());
                Message msg = (Message) in.readObject();
                this.notifyListenersMessageSent(msg);
                Log.i(TAG, String.format("Sending message: %s", msg));
            }
        } catch (EOFException e) {
            Log.i(TAG, "Connected closed by server");
            return;
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.toString());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // Ignore, about to exit.
                return;
            }
        }
    }


    /**
     * notify all listeners of successful connection to server
     * 
     * @param host
     *            IP address of the ChatServer
     * @param port
     *            being used by the ChatServer
     */
    private void notifyListenersConnectedToServer(String host, int port) {
        for (ClientChangeListener listener : this.listeners) {
            listener.startChat(getUsername(), Integer.toString(port), host);
        }
    }


    /**
     * notify all listeners that a message was sent
     * 
     * @param message
     *            text of message being sent
     */
    private void notifyListenersMessageSent(Message message) {
        for (ClientChangeListener listener : this.listeners) {
            listener.messageReceived(message);
        }
    }
}