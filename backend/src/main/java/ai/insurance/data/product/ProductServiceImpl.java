package ai.insurance.data.product;

import ai.insurance.domain.product.Product;
import ai.insurance.domaininteraction.product.ProductService;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @WithSession
    public Uni<List<Product>> getAllProducts() {
        return productRepository.listAll();
    }

    @Override
    @WithSession
    public Uni<List<Product>> findProductsByIds(Set<UUID> insuranceIds) {
        return productRepository.findProductsByIds(insuranceIds);
    }
}
