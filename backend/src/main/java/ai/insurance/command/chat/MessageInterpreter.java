package ai.insurance.command.chat;

import ai.insurance.ai.ChatBot;
import io.quarkus.websockets.next.WebSocketConnection;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class MessageInterpreter {

    private final ChatBot chatBot;
    private final MessageSender messageSender;

    public MessageInterpreter(ChatBot chatBot, MessageSender messageSender) {
        this.chatBot = chatBot;
        this.messageSender = messageSender;
    }


    public void interpretMessage(WebsocketMessage message, WebSocketConnection connection) {
        if(message.getTopic() == WebSocketTopic.CHAT) {
            String response = chatBot.chat(message.getUserId(), message.getMessage());

            messageSender.sendChatMessageToUI(response, connection);
        }
    }
}
