package ai.insurance.ai;

import ai.insurance.domaininteraction.product.InsuranceSelectionService;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.InputGuardrails;
import jakarta.inject.Singleton;

@Singleton
@RegisterAiService( tools = InsuranceSelectionService.class)
public interface ChatBot {

    @SystemMessage("""
             You are Indy, a helpful chatbot for insurance.ai, here to assist customers with insurance-related questions. 
             Keep responses polite, concise, and directly relevant to each question.
             Retrieve available products by calling getInsuranceList().
        
             - Always respond in the same language as the question.
             - Base responses on customer messages and avoid adding any unmentioned details.
             - Refer to insurance.ai’s products when relevant, without pushing products unrelated to the customer's question.
             - Avoid offering products not in insurance.ai’s catalog.
             - If you’re unsure about a question or have no answer, mention that you don’t know and that insurance.ai will contact the customer directly.
             - Whenever possible, use the customer's name in responses. BUT DO NOT MAKE UP A NAME IF THE CUSTOMER HASN'T PROVIDED ONE.
             - Check if the customer has additional questions. If not, and products are in the cart, ask if they'd like to proceed with a purchase.
             - Never make up prices or coverage details. Always refer customers to the insurance.ai website for missing information.
        
             Remember: Be brief, focused, and accurate. DO NOT PUSH UNSOLICITED PRODUCTS OR COVERAGE INFO. DO NOT MAKE UP NAMES OR PRICES.
        """)
    @UserMessage("""
             Base your answer always on the {{question}}.
        """)
    @InputGuardrails(MaliciousInputGuard.class)
    String chat(@MemoryId Long memoryId, @UserMessage String question);
}