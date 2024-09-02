package java15.service;

import java15.dao.UserDAO;
import java15.model.User;
import java15.enums.Gender;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User registerUser(String name, String surname, String login, String password, Gender gender) {
        if (userDAO.findByLogin(login) != null) {
            throw new RuntimeException("Login already exists.");
        }
        return userDAO.createUser(name, surname, login, password, gender);
    }

    @Override
    public User findUserById(Long id) {
        return userDAO.readUser(id);
    }

    @Override
    public void updateUser(Long id, String name, String surname, String login, String password, Gender gender) {
        userDAO.updateUser(id, name, surname, login, password, gender);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userDAO.deleteUser(id);
    }

    @Override
    public User[] getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User authenticate(String login, String password) {
        User user = userDAO.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public User findUserByLogin(String login) {
        return userDAO.findByLogin(login);
    }
}
