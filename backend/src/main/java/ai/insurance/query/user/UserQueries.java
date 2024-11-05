package ai.insurance.query.user;

import ai.insurance.domain.user.User;
import ai.insurance.domain.user.UserService;
import ai.insurance.query.user.model.UserView;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;


import java.util.List;

public class UserQueries implements ai.insurance.query.user.api.UserQueriesApi {

    private final UserService userService;

    public UserQueries(UserService userService) {
        this.userService = userService;
    }

    @Override
    @WithSession
    public Uni<List<UserView>> getAllUsers() {
        return userService.getAllUsers()
            .onItem().transform(users -> users.stream()
                .map(this::createUserViewFromUser) // map User to UserView
                .toList()
            );
    }

    @Override
    @WithSession
    public Uni<UserView> getUserById(Long id) {
        return userService.getById(id)
            .map(this::createUserViewFromUser);
    }

    private UserView createUserViewFromUser(User user) {
        UserView userView = new UserView();
        userView.setId(user.getId());
        userView.setUsername(user.getUsername());
        userView.setFirstName(user.getFirstName());
        userView.setLastName(user.getLastName());

        return userView;
    }


}
