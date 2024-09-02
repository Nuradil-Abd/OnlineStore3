package java15.commands;

import java15.service.UserService;

public class CommandFactory {
    private final UserService userService;
    private final Command[] commands;

    public CommandFactory(UserService userService) {
        this.userService = userService;
        this.commands = new Command[] {
                new RegisterCommand(userService),
                new LoginCommand(userService),
                new UpdateUserCommand(userService),
                new DeleteUserCommand(userService),
                new ListUsersCommand(userService)
        };
    }

    public Command getCommand(int choice) {
        if (choice >= 1 && choice <= commands.length) {
            return commands[choice - 1];
        }
        return null;
    }
}
