package ai.insurance.command.chat;

import ai.insurance.ai.ChatBot;
import io.quarkus.websockets.next.OnClose;
import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import io.quarkus.websockets.next.WebSocketConnection;
import io.smallrye.mutiny.Multi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebSocket(path = "/chat/{userId}")
public class ChatWebSocket {

    public ChatWebSocket(WebsocketConnectionManager connectionManager, WebSocketConnection connection, ChatBot chatBot) {
        this.connectionManager = connectionManager;
        this.connection = connection;
        this.chatBot = chatBot;
    }

    // Declare the type of messages that can be sent and received

    private final WebsocketConnectionManager connectionManager;
    private final WebSocketConnection connection;
    private final ChatBot chatBot;

    @OnOpen
    public void onOpen(WebSocketConnection connection) {
        log.info("WebSocket opened: " + connection.id());
        connectionManager.addConnection(connection); // Add connection to manager
    }

    @OnClose
    public void onClose(WebSocketConnection connection) {
        log.info("WebSocket closed: " + connection.id());
        connectionManager.removeConnection(connection.id()); // Remove connection from manager
    }

    @OnTextMessage(broadcast = true)
    public Multi<String> onMessage(ChatInputDTO message) {
        return chatBot.chat(Integer.getInteger(connection.pathParam("userId")), message.message());
    }

}