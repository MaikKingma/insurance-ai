package ai.insurance.command.chat;

import ai.insurance.ai.ChatBot;
import io.quarkus.websockets.next.OnClose;
import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import io.quarkus.websockets.next.WebSocketConnection;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebSocket(path = "/chat/{userId}")
public class ChatWebSocket {

    private final WebsocketConnectionManager connectionManager;
    private final WebSocketConnection connection;
    private final ChatBot chatBot;

    public ChatWebSocket(WebsocketConnectionManager connectionManager, WebSocketConnection connection, ChatBot chatBot) {
        this.connectionManager = connectionManager;
        this.connection = connection;
        this.chatBot = chatBot;
    }

    @OnOpen
    public void onOpen(WebSocketConnection connection) {
        log.info("WebSocket opened: {}", connection.id());
        connectionManager.addConnection(connection); // Add connection to manager
    }

    @OnClose
    public void onClose(WebSocketConnection connection) {
        log.info("WebSocket closed: {}",connection.id());
        connectionManager.removeConnection(connection.id()); // Remove connection from manager
    }

    //TODO try to make this return Multi<String> instead of String
    @OnTextMessage(broadcast = true)
    public String onMessage(String message) {

        log.info("WebSocket in progress: {}", connection.id());
        String userId = connection.pathParam("userId");

        return chatBot.chat(Long.parseLong(userId), message);
    }
}