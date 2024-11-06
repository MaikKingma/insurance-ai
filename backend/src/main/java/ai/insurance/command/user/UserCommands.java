package ai.insurance.command.user;

import ai.insurance.command.user.api.UserCommandsApi;
import ai.insurance.command.user.model.UserSignUpPayload;
import ai.insurance.domain.user.User;
import ai.insurance.domaininteraction.user.UserService;
import jakarta.transaction.Transactional;

public class UserCommands implements UserCommandsApi {
    private final UserService userService;

    public UserCommands(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public void signUpUser(UserSignUpPayload userSignUpPayload) {
        userService.createUser(User.create(
                userSignUpPayload.getUsername(),
                userSignUpPayload.getFirstName(),
                userSignUpPayload.getLastName()
            )
        );
    }
}
