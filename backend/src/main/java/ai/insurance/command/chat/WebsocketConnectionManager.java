package ai.insurance.command.chat;

import io.quarkus.websockets.next.WebSocketConnection;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ApplicationScoped
public class WebsocketConnectionManager {

    private final Map<String, WebSocketConnection> connections = new ConcurrentHashMap<>();
    private String sessionUserId;

    public void addConnection(WebSocketConnection connection) {
        String userId = connection.pathParam("userId");
        connections.put(userId, connection);
        sessionUserId = userId;
    }

    public void removeConnection(String connectionId) {
        connections.remove(connectionId);
        sessionUserId = null;
    }

    public WebSocketConnection getCurrentConnectionSession() {
        if (sessionUserId == null) {
            log.error("No session user id found");
            throw new IllegalStateException("No session user id found");
        }

        return connections.get(sessionUserId);
    }

    public Long getCurrentUserId() {
        return Long.parseLong(sessionUserId);
    }
}
