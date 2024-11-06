package ai.insurance.domaininteraction.product;

import ai.insurance.domain.product.Product;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ProductJDBCService {
    List<Product> getAllProducts();

    List<Product> findProductsByIds(Set<UUID> insuranceIds);
}
