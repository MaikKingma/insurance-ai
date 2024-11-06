package ai.insurance.data.product;

import ai.insurance.domain.product.CoverageCategory;
import ai.insurance.domain.product.InsuranceCategory;
import ai.insurance.domain.product.Product;
import ai.insurance.domaininteraction.product.ProductJDBCService;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class ProductJDBCServiceImpl implements ProductJDBCService {
    private final AgroalDataSource dataSource;

    public ProductJDBCServiceImpl(AgroalDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(UUID.fromString(rs.getString("id")));
                product.setName(rs.getString("name"));
                product.setDefaultDescription(rs.getString("description"));
                product.setCoverageDescription(rs.getString("coverage_description"));
                product.setCategory(InsuranceCategory.valueOf(rs.getString("category")));
                product.setCoverageCategory(CoverageCategory.valueOf(rs.getString("coverage_category")));
                // Set other fields as needed
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        return products;
    }

    @Transactional
    public List<Product> findProductsByIds(Set<UUID> ids) {
        return null;
    }
}
