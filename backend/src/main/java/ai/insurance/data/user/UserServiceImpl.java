package ai.insurance.data.user;

import ai.insurance.domain.user.User;
import ai.insurance.domaininteraction.user.UserService;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.With;

import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @WithSession
    public Uni<List<User>> getAllUsers() {
        return userRepository.listAll();
    }

    @Override
    @WithTransaction
    public Uni<User> createUser(User user) {
        return userRepository.persist(user);
    }

    @Override
    @WithSession
    public Uni<User> getById(Long id) {
        return userRepository.findById(id);
    }
}
