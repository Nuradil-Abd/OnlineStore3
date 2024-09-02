package java15.commands;

import java15.service.UserService;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CommandRunner {
    private final UserService userService;
    private final CommandFactory commandFactory;

    public CommandRunner(UserService userService) {
        this.userService = userService;
        this.commandFactory = new CommandFactory(userService);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nUser Management System");
            System.out.println("1. Register New User");
            System.out.println("2. Login");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. List All Users");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
                continue;
            }

            if (choice == 6) {
                System.out.println("Exiting...");
                scanner.close();
                return;
            }

            Command command = commandFactory.getCommand(choice);
            if (command != null) {
                command.execute(scanner);
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
