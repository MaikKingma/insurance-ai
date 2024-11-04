package ai.insurance.command;


import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import ai.insurance.ai.Bot;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/chat")
public class ChatCommands {

    private final Bot bot;

    public ChatCommands(Bot bot) {
        this.bot = bot;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<String> chat(ChatInputDTO input) {
        return Uni.createFrom().item(() -> bot.chat(1, input.message()));
    }
}
