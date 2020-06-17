package java2.server.networkserver;

import java2.server.networkserver.auth.AuthService;
import java2.server.networkserver.auth.BaseAuthService;
import java2.server.networkserver.clienthandler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

    private int port;
    private List<ClientHandler> clients;
    private AuthService authService;

    public MyServer(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
        this.authService = new BaseAuthService();
    }

    public void start () {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running");
            authService.start();
            //noinspection InfiniteLoopStatement
            while (true) {
                System.out.println("Server is waiting connection....");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client has been connected");
                ClientHandler handler = new ClientHandler(clientSocket, this);
                try {
                    handler.handle();
                } catch (IOException e) {
                    System.err.println("Filed to handle client connection!");
                    clientSocket.close();
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            authService.stop();
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNicknameBusy(String nickname) {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastMessage(String message) throws IOException {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public synchronized void privateMessage(String nickname, String message) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(nickname)) {
            client.sendMessage(message);
            }
        }
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }
}
