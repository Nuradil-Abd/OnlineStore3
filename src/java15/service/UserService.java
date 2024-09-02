package java15.service;

import java15.model.User;
import java15.enums.Gender;

public interface UserService {
    User registerUser(String name, String surname, String login, String password, Gender gender);
    User findUserById(Long id);
    void updateUser(Long id, String name, String surname, String login, String password, Gender gender);
    boolean deleteUser(Long id);
    User[] getAllUsers();
    User authenticate(String login, String password);
    User findUserByLogin(String login);
}
