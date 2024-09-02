package java15;
import java15.dao.UserDAO;
import java15.repository.Database;
import java15.service.UserService;
import java15.service.UserServiceImpl;
import java15.commands.CommandRunner;

public class Main {
    public static void main(String[] args) {

        //crud
        // c - > create
        // r -> read
        // u -> update
        // d -> delete

        UserDAO userDAO = new Database();
        UserService userService = new UserServiceImpl(userDAO);
        CommandRunner commandRunner = new CommandRunner(userService);
        commandRunner.run();


    }
}