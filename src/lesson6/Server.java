package lesson6;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static final int SERVER_PORT = 8189;
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_GREEN = "\u001B[32m";


    private static void processSendMessagesToClient() {
        new Thread(() -> {
            while (true) {
                String message = sendMessageToClient();
                try {
                    outputStream.writeUTF(message);
                } catch (IOException e) {
                    Client.showErrorMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static String sendMessageToClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сообщение для Client");
        String message = scanner.nextLine();
        return message;
    }

    private static void processMessagesFromClient() {
        new Thread(() -> {
            try {
                while (true) {
                    String message = inputStream.readUTF();
                    appendMessageForClient("Client", message);
                }
            } catch (IOException e) {
                System.err.println("Client был закрыт!");
                e.printStackTrace();
            }
        }).start();
    }

    private static void appendMessageForClient (String sender, String message) {
        System.out.println(Server.COLOR_BLUE + sender + " прислал сообщение: " + message + Server.COLOR_RESET);
        System.out.println("Введите сообщение для Client: ");

    }

    public static void main(String[] args) {
        System.out.println("Старт...");
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Сервер запущен. Ожидаем подключения....");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Клиент подключился!");

            inputStream = new DataInputStream(clientSocket.getInputStream());
            outputStream = new DataOutputStream(clientSocket.getOutputStream());

            processSendMessagesToClient();
            processMessagesFromClient();

            serverSocket.close();

        } catch (IOException e) {
            System.err.println("Port " + SERVER_PORT + " is already used");
            e.printStackTrace();
        }
    }


}
