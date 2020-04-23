package java2.client.view.chat;

import java2.client.controller.ClientController;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java2.client.view.chat.ClientChat.*;

public class ClientChatAction extends MyWindowChat {

    public final int WINDOW_SIZE_X= 600;
    public final int WINDOW_SIZE_Y= 500;
    public final int WINDOW_START_X = 800;
    public final int WINDOW_START_Y = 100;

    private ClientController controller;

    public ClientChatAction(ClientController controller) {
        this.controller = controller;
        setTitle(controller.getUsername());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WINDOW_START_X, WINDOW_START_Y, WINDOW_SIZE_X, WINDOW_SIZE_Y);
        setResizable(true);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        addListeners();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                controller.shutdown();
            }
        });
    }


    public void addListeners() {
        sendButton.addActionListener(e -> sendMessage());
        entryField.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        String message = entryField.getText().trim();
        if (message.isEmpty()) {
            return;
        }

        appendOwnMessage(message);
        controller.sendMessage(message);
        entryField.setText(null);
    }

    public void appendMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            ClientChat.chatMessages.append(message);
            chatMessages.append(System.lineSeparator());
        });
    }


    private void appendOwnMessage(String message) {
        appendMessage("Я: " + message);
    }

}
