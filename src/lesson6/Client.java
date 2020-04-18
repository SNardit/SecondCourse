package lesson6;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private static final String HOSTNAME = "localhost";

    public Client() {
        initServerConnection();
    }

    private void initServerConnection() {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(HOSTNAME, Server.SERVER_PORT), 10_000);

            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            processSendMessagesToServer();
            processMessagesFromServer();

        } catch (IOException e) {
            showErrorMessage(e.getMessage());
        }
    }

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private void processMessagesFromServer() {
        new Thread(() -> {
            try {
                while (true) {
                    String messageFromServer = inputStream.readUTF();
                    appendMessageForServer("Server", messageFromServer);

                }
            } catch (IOException e) {
                System.err.println("Соединение с сервером было закрыто!");
                e.printStackTrace();
            }
        }).start();
    }


    private void appendMessageForServer (String sender, String message) {
        System.out.println(Server.COLOR_GREEN + sender + " прислал сообщение: " + message + Server.COLOR_RESET);
        System.out.println("Введите сообщение для Server: ");

    }

    private void processSendMessagesToServer() {
        new Thread(() -> {
            while (true) {
                String message = sendMessageToServer();
                try {
                    outputStream.writeUTF(message);
                } catch (IOException e) {
                    showErrorMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String sendMessageToServer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сообщение для Server: ");
        String message = scanner.nextLine();
        return message;
    }


    public static void main(String[] args) {
        new Client();
    }
}
