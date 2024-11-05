package ai.insurance.command.chat;


import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import ai.insurance.ai.ChatBot;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/chat")
public class ChatCommands {

    private final ChatBot chatBot;

    public ChatCommands(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<String> chat(ChatInputDTO input) {
        return chatBot.chat(1, input.message());
    }
}
