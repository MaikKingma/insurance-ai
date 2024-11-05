package ai.insurance.domain.product;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product extends PanacheEntityBase {

    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "default_description", nullable = false)
    private String defaultDescription;

    @Column(name = "coverage_description", nullable = false)
    private String coverageDescription;

    @Column(name = "prices", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Set<Price> prices = new HashSet<>();

    @Column(name = "category", nullable = false)
    private InsuranceCategory category;

    @Column(name = "coverage_category", nullable = false)
    private CoverageCategory coverageCategory;

    public static Product create(String name, String defaultDescription, String coverageDescription, Double monthlyPrice, Double yearlyPrice, InsuranceCategory category) {
        Product product = new Product();
        product.id = UUID.randomUUID();
        product.name = name;
        product.defaultDescription = defaultDescription;
        product.coverageDescription = coverageDescription;
        product.prices = Set.of(new Price(monthlyPrice, PaymentCycle.MONTHLY), new Price(yearlyPrice, PaymentCycle.YEARLY));
        product.category = category;

        return product;
    }

    public record Price (Double amount, PaymentCycle yearlyPrice) {
    }
}
