package java15.commands;

import java15.service.UserService;
import java15.model.User;
import java15.enums.Gender;

import java.util.Scanner;

public class RegisterCommand implements Command {
    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(Scanner scanner) {
        String name = promptForValidInput(scanner, "Enter name: ", this::validateName);
        if (name == null) return;

        String surname = promptForValidInput(scanner, "Enter surname: ", this::validateSurname);
        if (surname == null) return;

        String login = promptForValidInput(scanner, "Enter login: ", this::validateLogin);
        if (login == null) return;

        String password = promptForValidInput(scanner, "Enter password: ", this::validatePassword);
        if (password == null) return;

        Gender gender = promptForValidGender(scanner);
        if (gender == null) return;

        try {
            User newUser = userService.registerUser(name, surname, login, password, gender);
            System.out.println("User registered: " + newUser);
        } catch (Exception e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }

    private String promptForValidInput(Scanner scanner, String prompt, Validator validator) {
        int attempts = 0;
        String input;
        while (attempts < 3) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (validator.validate(input)) {
                return input;
            } else {
                attempts++;
                System.out.println("Invalid input. Attempt " + attempts + " of 3.");
            }
        }
        System.out.println("Maximum attempts reached. Exiting.");
        return null;
    }

    private String promptForUniqueLogin(Scanner scanner) {
        int attempts = 0;
        String login;
        while (attempts < 3) {
            System.out.print("Enter login: ");
            login = scanner.nextLine();
            if (login.length() > 13 && login.endsWith("@gmail.com")) {
                if (userService.findUserByLogin(login) == null) {
                    return login;
                } else {
                    System.out.println("Login already exists. Attempt " + (attempts + 1) + " of 3.");
                }
            } else {
                System.out.println("Invalid login format. Attempt " + (attempts + 1) + " of 3.");
            }
            attempts++;
        }
        System.out.println("Maximum attempts reached. Exiting.");
        return null;
    }


    private Gender promptForValidGender(Scanner scanner) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter gender (MALE/FEMALE): ");
            String genderStr = scanner.nextLine().toUpperCase();
            try {
                return Gender.valueOf(genderStr);
            } catch (IllegalArgumentException e) {
                attempts++;
                System.out.println("Invalid gender. Attempt " + attempts + " of 3.");
            }
        }
        System.out.println("Maximum attempts reached. Exiting.");
        return null;
    }

    private boolean validateName(String name) {
        return name.length() >= 3 && Character.isUpperCase(name.charAt(0));
    }

    private boolean validateSurname(String surname) {
        return surname.length() >= 3 && Character.isUpperCase(surname.charAt(0));
    }

    private boolean validateLogin(String login) {
        return login.length() > 13 && login.endsWith("@gmail.com");
    }

    private boolean validatePassword(String password) {
        return password.length() >= 8 && containsUpperCase(password) && containsDigit(password);
    }

    private boolean containsUpperCase(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsDigit(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    @FunctionalInterface
    private interface Validator {
        boolean validate(String input);
    }
}
