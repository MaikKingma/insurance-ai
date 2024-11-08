package ai.insurance.domaininteraction.product;

import ai.insurance.command.chat.MessageSender;
import ai.insurance.domain.product.PaymentCycle;
import ai.insurance.domain.product.Product;
import ai.insurance.query.product.model.PriceView;
import ai.insurance.query.product.model.ProductView;
import dev.langchain4j.agent.tool.Tool;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.vertx.VertxContextSupport;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
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

    private final ProductJDBCService productJDBCService;
    private final MessageSender messageSender;

    public InsuranceSelectionService(ProductJDBCService productJDBCService, MessageSender messageSender) {
        this.productJDBCService = productJDBCService;
        this.messageSender = messageSender;
    }

    @Tool
    public String getInsuranceList() throws Throwable {
        log.info("Calling getInsuranceList()");
        List<Product> products = productJDBCService.getAllProducts();
        return products.stream().map(Product::toJson).collect(Collectors.joining(","));
    }

    @Tool
    public void proposeInsuranceSelection(String idList) {
        log.info("Calling proposeInsuranceSelection() with idList: " + idList);
        Set<UUID> insuranceIds = Stream.of(idList.split(","))
                .map(UUID::fromString)
                .collect(Collectors.toSet());
        List<Product> chosenProducts = productJDBCService.findProductsByIds(insuranceIds);
        messageSender.sendInsuranceSelectionToUI(chosenProducts.stream().map(this::createProductViewFromProduct).toList());

    }

    private ProductView createProductViewFromProduct(Product product) {
        ProductView productView = new ProductView();
        productView.setId(product.getId().toString());
        productView.setName(product.getName());
        productView.setDescription(product.getDefaultDescription());
        productView.setCoverageDescription(product.getCoverageDescription());
        productView.setCategory(product.getCategory().name());
        productView.setCoverageCategory(product.getCoverageCategory().name());
        productView.setPrice(product.getPrices().stream().map(price -> {
            PriceView priceView = new PriceView();
            priceView.setAmount(price.amount());
            priceView.setPaymentCycle(price.paymentCycle().name());
            return priceView;
        }).toList());
        return productView;
    }
}
