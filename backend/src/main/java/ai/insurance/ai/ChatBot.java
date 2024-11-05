package ai.insurance.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.smallrye.mutiny.Multi;

@RegisterAiService
public interface ChatBot {

    @SystemMessage("""
         You are an AI named Jaap answering questions about insurance products. If you ask the client a question,\s
         remember the answer.
         Your response must be polite, use the same language as the question, and be relevant to the question.
         The context is a conversation with your client, so you must remember the questions asked and answers given.
         When you don't know, respond that you don't know the answer and insurance.ai will contact the customer\s
         directly via email with a solution. If the user is not logged in, then you should invite him to provide his\s
         email so you can send the requested info as soon as it is available.
        """)
    Multi<String> chat(@MemoryId int memoryId, @UserMessage String question);
}