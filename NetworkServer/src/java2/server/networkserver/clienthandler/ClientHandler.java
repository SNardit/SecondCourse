package java2.server.networkserver.clienthandler;

import java2.server.networkserver.MyServer;
import java2.server.networkserver.auth.AuthService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static java2.server.networkserver.MessageConstant.*;

public class ClientHandler {

    private final MyServer serverInstance;
    private final Socket clientSocket;
    private final AuthService authService;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String nickname;

    public ClientHandler(Socket clientSocket, MyServer myServer) {
        this.clientSocket = clientSocket;
        this.serverInstance = myServer;
        this.authService = serverInstance.getAuthService();

    }

    public void handle() throws IOException {
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());

        new Thread(()-> {
            try {
                authentication();
                readMessage();
            } catch (IOException e) {
                System.err.println("Connection has been failed!");
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }).start();
    }

    private void closeConnection() {
        serverInstance.unsubscribe(this);
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            if (message.startsWith(END_CMD)) {
                return;
            }
            if (message.startsWith("/w")) {
                String [] parts = message.split("\\s+", 3);
                String nickname = parts[1];
                String messageForSent = parts[2];
                serverInstance.privateMessage(nickname, messageForSent);
            }
            else
            {
                serverInstance.broadcastMessage(String.format("%s: %s", nickname, message));
            }
        }
    }

    private void authentication() throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            if (message.startsWith(AUTH_CMD)) {
                String[] parts = message.split("\\s+");
                String login = parts[1];
                String password = parts[2];

                String nickname = authService.getNickByLoginAndPassword(login, password);
                if (nickname == null) {
                    sendMessage("Wrong login or/and password!");

                }
                else if (serverInstance.isNicknameBusy(nickname)) {
                    sendMessage("Account is already using!");
                }
                else {
                    sendMessage(String.format("%s %s", AUTH_SUCCESSFUL_CMD, nickname));
                    setNickname(nickname);
                    serverInstance.broadcastMessage(nickname + " is online!");
                    serverInstance.subscribe(this);
                    break;
                }
            }
        }
    }

    private void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void sendMessage(String message) throws IOException {
        outputStream.writeUTF(message);
    }
}
