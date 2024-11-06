package ai.insurance.query.user;

import ai.insurance.domain.user.User;
import ai.insurance.domaininteraction.user.UserService;
import ai.insurance.query.user.model.UserView;

import java.util.List;

public class UserQueries implements ai.insurance.query.user.api.UserQueriesApi {

    private final UserService userService;

    public UserQueries(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserView> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(this::createUserViewFromUser) // map User to UserView
                .toList();
    }

    @Override
    public UserView getUserById(Long id) {
        return createUserViewFromUser(userService.getById(id));
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
