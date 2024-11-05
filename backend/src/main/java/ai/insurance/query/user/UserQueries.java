package ai.insurance.query.user;

import ai.insurance.domain.user.UserService;
import ai.insurance.query.user.model.UserView;
import io.smallrye.mutiny.Uni;

import java.util.List;

public class UserQueries implements ai.insurance.query.user.api.UserQueriesApi {

    private final UserService userService;

    public UserQueries(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Uni<List<UserView>> usersGet() {
        return null;
    }

    @Override
    public Uni<UserView> usersIdGet(Integer id) {
        return null;
    }
}
