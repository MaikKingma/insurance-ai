package ai.insurance.domaininteraction.product;

import ai.insurance.domain.product.Product;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface ProductService {
    Uni<List<Product>> getAllProducts();
}
