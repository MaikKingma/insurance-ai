package ai.insurance.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.smallrye.mutiny.Multi;

/**
 * This is a sample Bot, it is configured to ingest the 'easy-rag-catalog/'.
 * You can @Inject this Bot in your Rest resource
 *
 * \{@code
 *     @Inject
 *     Bot bot;
 *
 *     @POST
 *     @Produces(MediaType.TEXT_PLAIN)
 *     public String chat(String q) {
 *         return bot.chat(q);
 *     }
 * }
 */
@RegisterAiService // no need to declare a retrieval augmentor here, it is automatically generated and discovered
public interface Bot {

    @SystemMessage("""
            You are an AI named Bob answering questions about insurance products.
            Your response must be polite, use the same language as the question, and be relevant to the question.

            When you don't know, respond that you don't know the answer and insurance.ai will contact the customer directly.
            """)
    Multi<String> chat(@MemoryId int memoryId, @UserMessage String question);
}