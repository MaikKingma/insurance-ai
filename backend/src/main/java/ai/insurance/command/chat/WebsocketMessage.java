package ai.insurance.command.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
public class WebsocketMessage {
    private WebSocketTopic topic;
    private Long userId;
    private String message;

    public String toJson() {
        return "{\"topic\":\"" + topic + "\",\"userid\":" + userId + ",\"message\":\"" + message + "\"}";
    }
}
