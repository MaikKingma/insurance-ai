package ai.insurance.data.product;

import ai.insurance.domain.product.Product;
import ai.insurance.domaininteraction.product.ProductService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Uni<List<Product>> getAllProducts() {
        return productRepository.listAll();
    }
}
