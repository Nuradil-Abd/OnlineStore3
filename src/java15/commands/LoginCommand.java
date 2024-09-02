package java15.commands;

import java15.service.UserService;
import java15.model.User;

import java.util.Scanner;

public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.print("Enter login: ");
        String login = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userService.authenticate(login, password);

        if (user != null) {
            System.out.println("Login successful. Welcome, " + user.getName() + "!");
        } else {
            System.out.println("Invalid login or password.");
        }
    }
}
