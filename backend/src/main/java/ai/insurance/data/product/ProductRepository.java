package ai.insurance.data.product;

import ai.insurance.domain.product.Product;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    // find products by their ids
    public Uni<List<Product>> findProductsByIds(Set<UUID> insuranceIds) {
        return find("id IN :ids", Parameters.with("ids", insuranceIds)).list();
    }
}
