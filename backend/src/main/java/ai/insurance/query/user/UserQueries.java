package ai.insurance.query.user;

import ai.insurance.domain.user.User;
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
    public Uni<List<UserView>> getAllUsers() {
        return userService.getAllUsers()
            .onItem().transform(users -> users.stream()
                .map(this::createUserViewFromUser) // map User to UserView
                .toList()
            );
    }

    private UserView createUserViewFromUser(User user) {
        UserView userView = new UserView();
        userView.setId(user.getId());
        userView.setUsername(user.getUsername());
        userView.setFirstName(user.getFirstName());
        userView.setLastName(user.getLastName());

        return userView;
    }

    @Override
    public Uni<UserView> getUserById(Integer id) {
        return null;
    }
}
