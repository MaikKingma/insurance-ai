package ai.insurance.command.chat;

import java.util.Locale;

public enum WebSocketTopic {
    CHAT,
    PROPOSED_INSURANCES,
    CART,
    PURCHASE,
    ERROR;

    public static WebSocketTopic fromString(String topic) {
        return switch (topic.toLowerCase(Locale.ROOT)) {
            case "chat" -> CHAT;
            case "proposed_insurances" -> PROPOSED_INSURANCES;
            case "cart" -> CART;
            case "purchase" -> PURCHASE;
            default -> throw new IllegalArgumentException("Invalid topic: " + topic);
        };
    }
}
