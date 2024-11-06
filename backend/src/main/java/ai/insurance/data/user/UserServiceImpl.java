package ai.insurance.data.user;

import ai.insurance.domain.user.User;
import ai.insurance.domaininteraction.user.UserService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Uni<List<User>> getAllUsers() {
        return userRepository.listAll();
    }

    @Override
    public Uni<User> createUser(User user) {
        return userRepository.persist(user);
    }

    @Override
    public Uni<User> getById(Long id) {
        return userRepository.findById(id);
    }
}
