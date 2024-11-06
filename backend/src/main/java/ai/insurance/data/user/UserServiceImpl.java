package ai.insurance.data.user;

import ai.insurance.domain.user.User;
import ai.insurance.domaininteraction.user.UserService;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.listAll();
    }

    @Override
    public void createUser(User user) {
        userRepository.persist(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id);
    }
}
