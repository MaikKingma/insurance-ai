package ai.insurance.domaininteraction.user;

import ai.insurance.domain.user.User;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface UserService {
    Uni<List<User>> getAllUsers();

    Uni<User> createUser(User user);

    Uni<User> getById(Long id);
}
