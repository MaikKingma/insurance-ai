package ai.insurance.ai;

import ai.insurance.domaininteraction.product.InsuranceSelectionService;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.InputGuardrails;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RegisterAiService
public interface ChatBot {
//
//    @UserMessage("""
//        Base your answer always on the customer message {question} and avoid adding any unmentioned details.
//
//        ---
//        From the suggested products NEVER relay the internal ID of the product, only the name, descriptions, price and categories.
//        In the first 3 messages, use the client's name.
//        Always respond in the same language as the question.
//        Maintain a polite and professional tone in all communications.
//        Base your answers solely on the customer's messages and the information from the database. Do not add details that have not been mentioned.
//        If you cannot answer a question or do not know the answer, honestly admit that you do not know and inform the customer that insurance.ai will contact them directly.
//        At the end of the conversation, thank the customer for their time and offer assistance in the future.
//        Answer each question separately to provide clarity.
//        Use neutral language and avoid jargon that is not widely known.
//    """)

//Always first retrieve the available insurances by calling getInsuranceList().
    //- Never make up insurances, prices or coverage details. getInsuranceList() is the source of truth for insurance products.
    //Once you picked the insurances for the customer, call proposeInsuranceSelection once with a comma-separated list of all insurance ids (one of each) the client needs (this will display the insurances to the user).
    @SystemMessage("""
        You are Indy, a helpful chatbot for insurance.ai, here to assist customers with insurance-related questions.
        Keep responses polite, concise, and directly relevant to each question.
        You determine what the customer is looking for in terms of insurance.
    
        If there are multiple viable options, you will pick the best matching insurances based on insurance category, coverage category and price/quality.
        In your answer you will explain why you proposed the one you picked.
        
        - Always respond in the same language as the question.
        - Avoid offering products not in insurance.ai’s catalog.
    
        Remember: Be brief, focused, and accurate. DO NOT PUSH UNSOLICITED PRODUCTS OR COVERAGE INFO. DO NOT MAKE UP NAMES OR PRICES.
    """)
    @InputGuardrails(MaliciousInputGuard.class)
    String chat(@MemoryId Long memoryId, @UserMessage String question);
}