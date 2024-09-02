package java15.commands;

import java15.service.UserService;
import java15.model.User;

import java.util.Scanner;

public class ListUsersCommand implements Command {
    private final UserService userService;

    public ListUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(Scanner scanner) {
        User[] users = userService.getAllUsers();
        if (users.length == 0) {
            System.out.println("No users found.");
            return;
        }

        System.out.println("List of users:");
        for (User user : users) {
            System.out.println(user);
        }
    }
}
