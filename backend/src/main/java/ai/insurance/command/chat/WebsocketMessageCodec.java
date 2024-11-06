package ai.insurance.command.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.websockets.next.TextMessageCodec;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

@Slf4j
@Singleton
public class WebsocketMessageCodec implements TextMessageCodec<WebsocketMessage> {
    @Override
    public boolean supports(Type type) {
        return type.equals(WebsocketMessage.class);
    }

    @Override
    public String encode(WebsocketMessage value) {
        return "";
    }

    @Override
    public WebsocketMessage decode(Type type, String message) {
        // Parse the received message as JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonMessage = objectMapper.readTree(message);
            // Extract action and data from the JSON
            WebSocketTopic topic = WebSocketTopic.fromString(jsonMessage.get("topic").asText());
            Long userId = jsonMessage.get("userId").asLong();

            return new WebsocketMessage(topic, userId, jsonMessage.get("message").asText());
        } catch (JsonProcessingException e) {
            log.error("Error parsing message: {}", e.getMessage());
            return null;
        }
    }


}
