package ai.insurance.domain.user;

import io.smallrye.mutiny.Uni;

public interface UserService {
    Uni<User> findByUsername(String username);
}
