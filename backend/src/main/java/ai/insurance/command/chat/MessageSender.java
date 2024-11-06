package ai.insurance.command.chat;

import ai.insurance.domain.product.Product;
import ai.insurance.query.product.model.ProductView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.websockets.next.WebSocketConnection;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@ApplicationScoped
public class MessageSender {

    private final WebsocketConnectionManager connectionManager;

    public MessageSender(WebsocketConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void sendChatMessageToUI(String message, WebSocketConnection connection) {
        String userId = connection.pathParam("userId");
        Long some = Long.parseLong(userId);
        log.info("Sending message to user: {} with connectionId: {}", userId, connection.id());
        sendChatMessageToUI(message, some, connection);
    }

    public void sendChatMessageToUI(String message, Long userId, WebSocketConnection connection) {
        try {
            WebsocketMessage websocketMessage = new WebsocketMessage(WebSocketTopic.CHAT, userId, message);
            connection.sendTextAndAwait(websocketMessage.toJson());
        } catch (Exception e) {
            log.error("Failed to send message to connection: {}", connection.id(), e);
        }
    }

    public void sendInsuranceSelectionToUI(List<ProductView> chosenProducts) {
        log.info("Sending insurance selection to UI");
        String message = chosenProducts.stream()
                .map(this::createJsonString)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        new WebsocketMessage(WebSocketTopic.PROPOSED_INSURANCES, connectionManager.getCurrentUserId(), message);
        connectionManager.getCurrentConnectionSession().sendTextAndAwait(message);
    }

    private String createJsonString(ProductView productView) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return ""; // ignore unparseable products
        }
    }
}
