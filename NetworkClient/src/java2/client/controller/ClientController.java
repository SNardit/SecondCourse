package java2.client.controller;

import java2.client.model.NetworkService;
import java2.client.view.auth.AuthDialogAction;
import java2.client.view.chat.ClientChatAction;

import javax.swing.*;
import java.io.IOException;

public class ClientController {
    private final NetworkService networkService;
    private final AuthDialogAction authDialogAction;
    private final ClientChatAction clientChatAction;
    private String nickname;


    public ClientController(String serverHost, int serverPort) {
        this.networkService = new NetworkService(serverHost, serverPort, this);
        this.authDialogAction = new AuthDialogAction(this);
        this.clientChatAction = new ClientChatAction(this);
    }

    public void runApplication() throws IOException {
        connectToServer();
        runAuthProcess();
    }

    private void runAuthProcess() {
        networkService.setSuccessfulAuthEvent(nickname -> {
            ClientController.this.setUserName(nickname);
            ClientController.this.openChat();
        });
        authDialogAction.setVisible(true);
    }

    private void openChat() {
        authDialogAction.dispose();
        networkService.setMessageHandler(clientChatAction::appendMessage);
        clientChatAction.setVisible(true);
    }

    private void setUserName(String nickname) {
        SwingUtilities.invokeLater(() -> clientChatAction.setTitle(nickname));
        this.nickname = nickname;
    }

    private void connectToServer() throws IOException {
        try {
            networkService.connect();
        } catch (IOException e) {
            System.err.println("Failed to establish server connection");
           throw e;
        }
    }

    public void sendAuthMessage(String login, String pass) throws IOException {
        networkService.sendAuthMessage(login, pass);
    }

    public void sendMessage(String message) {
        try {
            networkService.sendMessage(message);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to send message!");
            e.printStackTrace();
        }
    }

    public void shutdown() {
        networkService.close();
    }

    public String getUsername() {
        return nickname;
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(authDialogAction, message);
    }


}
