package ai.insurance.data.user;

import ai.insurance.domain.user.User;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Uni<User> findByUsername(String username) {
        return find("username", username).firstResult();
    }
}