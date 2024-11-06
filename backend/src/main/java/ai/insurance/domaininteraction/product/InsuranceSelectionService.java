package ai.insurance.domaininteraction.product;

import ai.insurance.command.chat.MessageSender;
import ai.insurance.domain.product.PaymentCycle;
import ai.insurance.domain.product.Product;
import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@ApplicationScoped
public class InsuranceSelectionService {

    private final ProductService productService;
    private final MessageSender messageSender;

    public InsuranceSelectionService(ProductService productService, MessageSender messageSender) {
        this.productService = productService;
        this.messageSender = messageSender;
    }

    @Tool
    public String getInsuranceList() {
        log.info("Calling getInsuranceList()");
        List<Product> products = new ArrayList<>();
        productService.getAllProducts().subscribe().with(products::addAll);
        return products.stream()
                .map(Product::toJson)
                .collect(Collectors.joining("\n"));
    }

    @Tool
    public void proposeInsuranceSelection(String idList) {
        log.info("Calling proposeInsuranceSelection() with idList: " + idList);
        // parse idList into a list of UUIDs
        Set<UUID> insuranceIds = Stream.of(idList.split(","))
                .map(UUID::fromString)
                .collect(Collectors.toSet());
        List<Product> chosenProducts = new ArrayList<>();
        productService.findProductsByIds(insuranceIds).subscribe().with(chosenProducts::addAll);

    }
}
