package ai.insurance.command.chat;

import io.quarkus.websockets.next.WebSocketConnection;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class MessageSender {

    public void sendChatMessageToUI(String message, WebSocketConnection connection) {
        String userId = connection.pathParam("userId");
        Long some = Long.parseLong(userId);
        log.info("Sending message to user: {} with connectionId: {}", userId, connection.id());
        sendChatMessageToUI(message, some, connection);
    }

    public void sendChatMessageToUI(String message, Long userId, WebSocketConnection connection) {
        try {
            WebsocketMessage websocketMessage = new WebsocketMessage(WebSocketTopic.CHAT, userId, message);
            connection.broadcast().sendTextAndAwait(websocketMessage.toJson());
        } catch (Exception e) {
            log.error("Failed to send message to connection: {}", connection.id(), e);
        }
    }
}
