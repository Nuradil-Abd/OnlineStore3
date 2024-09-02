package java15.commands;

import java15.service.UserService;

import java.util.Scanner;

public class DeleteUserCommand implements Command {
    private final UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.print("Enter user ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        boolean success = userService.deleteUser(id);
        if (success) {
            System.out.println("User deleted.");
        } else {
            System.out.println("User not found.");
        }
    }
}
