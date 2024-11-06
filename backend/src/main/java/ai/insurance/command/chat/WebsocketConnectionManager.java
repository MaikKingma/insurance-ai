package ai.insurance.command.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.websockets.next.WebSocketConnection;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ApplicationScoped
public class WebsocketConnectionManager {

    private final Map<String, WebSocketConnection> connections = new ConcurrentHashMap<>();

    public void addConnection(WebSocketConnection connection) {
        connections.put(connection.pathParam("userId"), connection);
    }

    public void removeConnection(String connectionId) {
        connections.remove(connectionId);
    }
}
