package ai.insurance.domaininteraction.user;

import ai.insurance.domain.user.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void createUser(User user);

    User getById(Long id);
}
