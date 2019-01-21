package server;

import authorization.AuthService;
import authorization.BaseAuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import static utils.Share.*;
import static utils.Share.currentTime;

public class ChatServer {
    private Hashtable<String, ClientHandler> clients =  new Hashtable<>();
    private AuthService authService = null;


    public void start(){

        authService = new BaseAuthService();

        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        boolean active = true;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Chat server started on port " + PORT);
            int colorIdx = 0;

            while(active) {
                clientSocket = serverSocket.accept();

                // Выделить новому клиенту цвет фона сообщений
                if(colorIdx == colors.length) colorIdx = 0;
                new ClientHandler(this, clientSocket, colors[colorIdx++]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Отправить сообщение всем клиентам
    public synchronized void broadcastMessage(String message) {
        Set<String> keys = clients.keySet();
        for(String key : keys) {
            ClientHandler ch = clients.get(key);
            if(ch.isActive()) ch.sendMessage(message);
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized boolean isNickBusy(String nickname) {
        return clients.get(nickname) != null;
    }

    public synchronized void subscribe(ClientHandler ch) {
        clients.put(ch.getNickname(), ch);
    }
    public synchronized void unsubscribe(ClientHandler ch) {
        clients.remove(ch.getNickname());
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.start();
    }
}
