package ai.insurance.command.chat;

import io.quarkus.websockets.next.OnClose;
import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import jakarta.enterprise.context.SessionScoped;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@SessionScoped
@WebSocket(path = "/websocket/{userId}")
public class InsuranceWebSocket implements Serializable {

    private final WebsocketConnectionManager connectionManager;
    private final io.quarkus.websockets.next.WebSocketConnection connection;
    private final MessageInterpreter messageInterpreter;
    private final MessageSender messageSender;

    public InsuranceWebSocket(WebsocketConnectionManager connectionManager, io.quarkus.websockets.next.WebSocketConnection connection, MessageInterpreter messageInterpreter, MessageSender messageSender) {
        this.connectionManager = connectionManager;
        this.connection = connection;
        this.messageInterpreter = messageInterpreter;
        this.messageSender = messageSender;
    }

    @OnOpen
    public void onOpen(io.quarkus.websockets.next.WebSocketConnection connection) {
        log.info("WebSocket opened: {}", connection.id());
        connectionManager.addConnection(connection); // Add connection to manager
        messageSender.sendChatMessageToUI("Hi! Welcome to insurance.ai. How can we assist you today?", connection);
    }

    @OnClose
    public void onClose(io.quarkus.websockets.next.WebSocketConnection connection) {
        log.info("WebSocket closed: {}",connection.id());
        connectionManager.removeConnection(connection.pathParam("userId")); // Remove connection from manager
    }

    @OnTextMessage(codec = WebsocketMessageCodec.class)
    public void consume(WebsocketMessage websocketMessage) {

        log.info("WebSocket in progress: {}", connection.id());
        log.info("Message received: {}", websocketMessage);

        messageInterpreter.interpretMessage(websocketMessage, connection);
    }
}