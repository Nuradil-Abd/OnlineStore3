package java15.commands;

import java15.service.UserService;
import java15.model.User;
import java15.enums.Gender;

import java.util.Scanner;

public class UpdateUserCommand implements Command {
    private final UserService userService;

    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.print("Enter user ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        User existingUser = userService.findUserById(id);
        if (existingUser == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Current information: " + existingUser);

        System.out.print("Enter new name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            name = existingUser.getName();
        }

        System.out.print("Enter new surname (leave blank to keep current): ");
        String surname = scanner.nextLine();
        if (surname.isEmpty()) {
            surname = existingUser.getSurname();
        }

        System.out.print("Enter new login (leave blank to keep current): ");
        String login = scanner.nextLine();
        if (login.isEmpty()) {
            login = existingUser.getLogin();
        }

        System.out.print("Enter new password (leave blank to keep current): ");
        String password = scanner.nextLine();
        if (password.isEmpty()) {
            password = existingUser.getPassword();
        }

        Gender gender = promptForValidGender(scanner, 3);
        if (gender == null) {
            gender = existingUser.getGender();
        }

        userService.updateUser(id, name, surname, login, password, gender);
        System.out.println("User updated.");
    }

    private Gender promptForValidGender(Scanner scanner, int maxAttempts) {
        int attempts = 0;
        while (attempts < maxAttempts) {
            System.out.print("Enter gender (MALE/FEMALE) or leave blank to keep current: ");
            String genderStr = scanner.nextLine().toUpperCase();
            if (genderStr.isEmpty()) {
                return null; // No change
            }
            try {
                return Gender.valueOf(genderStr);
            } catch (IllegalArgumentException e) {
                attempts++;
                System.out.println("Invalid gender. Attempt " + attempts + " of " + maxAttempts + ".");
            }
        }
        System.out.println("Maximum attempts reached. Exiting.");
        return null;
    }
}
