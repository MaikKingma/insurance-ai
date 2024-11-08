package ai.insurance.domaininteraction.product;

import ai.insurance.domain.product.Product;
import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ProductService {
    Uni<List<Product>> getAllProducts();

    Uni<List<Product>> findProductsByIds(Set<UUID> insuranceIds);
}
