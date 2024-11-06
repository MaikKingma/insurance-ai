package ai.insurance.query.product;

import ai.insurance.domain.product.Product;
import ai.insurance.domaininteraction.product.ProductJDBCService;
import ai.insurance.domaininteraction.product.ProductService;
import ai.insurance.query.product.api.ProductQueriesApi;
import ai.insurance.query.product.model.PriceView;
import ai.insurance.query.product.model.ProductView;
import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.stream.Collectors;

public class ProductQueries implements ProductQueriesApi {

    private final ProductService productService;
    private final ProductJDBCService productJDBCService;

    public ProductQueries(ProductService productService, ProductJDBCService productJDBCService) {
        this.productService = productService;
        this.productJDBCService = productJDBCService;
    }

    @Override
    public Uni<List<ProductView>> getAllProducts() {
        return productService.getAllProducts().onItem().transform(products -> products.stream()
            .map(this::createProductViewFromProduct) // map User to UserView
            .toList()
        );
//        return Uni.createFrom().item(productJDBCService.getAllProducts().stream()
//            .map(this::createProductViewFromProduct)
//            .toList()
//        );
    }

    @Override
    public Uni<List<ProductView>> getProductsByIds(List<String> id) {
        return productService.findProductsByIds(id.stream().map(java.util.UUID::fromString).collect(Collectors.toSet()))
            .onItem().transform(products -> products.stream()
                .map(this::createProductViewFromProduct)
                .toList()
        );
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
