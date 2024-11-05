package ai.insurance.data.user;

import ai.insurance.domain.user.User;
import ai.insurance.domain.user.UserService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Uni<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
