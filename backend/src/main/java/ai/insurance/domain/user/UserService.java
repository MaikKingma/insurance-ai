package ai.insurance.domain.user;

import io.smallrye.mutiny.Uni;

import java.util.List;

public interface UserService {
    Uni<List<User>> getAllUsers();

    Uni<User> createUser(User user);
}
