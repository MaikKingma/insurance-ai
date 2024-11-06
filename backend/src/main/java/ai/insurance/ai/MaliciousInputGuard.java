package ai.insurance.ai;

import dev.langchain4j.data.message.UserMessage;
import io.quarkiverse.langchain4j.guardrails.InputGuardrail;
import io.quarkiverse.langchain4j.guardrails.InputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MaliciousInputGuard implements InputGuardrail {

    @Inject
    HackerDetector hackerDetector;

    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        if(hackerDetector.isHacker(userMessage.singleText()) >= 0.4) {
            return fatal("Hacking attempt detected!");
        }

        return success();
    }
}
