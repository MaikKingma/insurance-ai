package ai.insurance.command.user;

import ai.insurance.command.user.api.UserCommandsApi;
import ai.insurance.command.user.model.UserSignUpPayload;
import ai.insurance.command.user.model.UserView;
import ai.insurance.domain.user.User;
import ai.insurance.domain.user.UserService;
import io.smallrye.mutiny.Uni;

public class UserCommands implements UserCommandsApi {
    private final UserService userService;

    public UserCommands(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Uni<UserView> signUpUser(UserSignUpPayload userSignUpPayload) {
        return userService.createUser(User.create(
            userSignUpPayload.getUsername(),
            userSignUpPayload.getFirstName(),
            userSignUpPayload.getLastName())).map(this::createUserViewFromUser);
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
