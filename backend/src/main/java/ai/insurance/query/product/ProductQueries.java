package ai.insurance.query.product;

import ai.insurance.domain.product.Product;
import ai.insurance.domaininteraction.product.ProductService;
import ai.insurance.query.product.api.ProductQueriesApi;
import ai.insurance.query.product.model.ProductView;
import io.smallrye.mutiny.Uni;

import java.util.List;

public class ProductQueries implements ProductQueriesApi {

    private final ProductService productService;

    public ProductQueries(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Uni<List<ProductView>> getAllProducts() {
        return productService.getAllProducts().onItem().transform(products -> products.stream()
            .map(this::createProductViewFromProduct) // map User to UserView
            .toList()
        );
    }

    private ProductView createProductViewFromProduct(Product product) {
        ProductView productView = new ProductView();
        productView.setId(product.getId().toString());
        productView.setName(product.getName());
        productView.setDescription(product.getDefaultDescription());
        return productView;
    }
}
