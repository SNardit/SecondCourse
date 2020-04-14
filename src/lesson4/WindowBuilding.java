package lesson4;


import javax.swing.*;
import java.awt.*;


public class WindowBuilding {
    public static final int WINDOW_SIZE = 500;
    public static final int WINDOW_START_X = 800;
    public static final int WINDOW_START_Y = 100;

    static class MyWindow extends JFrame {


        public MyWindow() {
            setTitle("Chat");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setBounds(WINDOW_START_X, WINDOW_START_Y, WINDOW_SIZE, WINDOW_SIZE);
            setResizable(true);
            setVisible(true);


            JPanel chatPanel = new JPanel();
            add(chatPanel, BorderLayout.CENTER);
            chatPanel.setLayout(new BorderLayout());
            JTextArea chatMessages = new JTextArea();

            JScrollPane scrollChatMessages = new JScrollPane(chatMessages);
            chatPanel.add(scrollChatMessages);
            chatMessages.setLineWrap(true);
            chatMessages.setWrapStyleWord(true);

            chatMessages.setBackground(new Color(240, 250, 250));
            chatMessages.setText("Ваши предыдущие сообщения: \n");
          //  chatMessages.setCaretColor(chatMessages.getBackground());
            //chatMessages.enableInputMethods(true);
            chatMessages.setEditable(false);


            JPanel sendMessagePanel = new JPanel();
            add(sendMessagePanel, BorderLayout.PAGE_END);
            sendMessagePanel.setLayout(new BorderLayout());
            sendMessagePanel.setBackground(new Color(100, 200, 170));

            JTextField entryField = new JTextField();
            sendMessagePanel.add(entryField, BorderLayout.CENTER);
            JButton sendButton = new JButton("Отправить");
            sendMessagePanel.add(sendButton, BorderLayout.EAST);

            Chat.addListeners(chatMessages, entryField, sendButton);
        }
    }


}






