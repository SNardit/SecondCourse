package lesson4;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Chat {

    public static void addListeners(JTextArea chatMessages, JTextField entryField, JButton sendButton) {
        sendButton.addActionListener(e -> Chat.sendMessage(entryField, chatMessages));
        entryField.addActionListener(e -> Chat.sendMessage(entryField, chatMessages));
        /*chatMessages.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                e.consume();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                e.consume();
            }
        });*/
    }

    public static void sendMessage(JTextField entryField, JTextArea chatMessages) {
        String message = entryField.getText().trim();
        if (!message.isEmpty()) {
            chatMessages.append(message + "\n");
            entryField.setText(null);
        }
    }
}
