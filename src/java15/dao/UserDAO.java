package java15.dao;

import java15.enums.Gender;
import java15.model.User;

public interface UserDAO {
    User createUser(String name, String surname, String login, String password, Gender gender);
    User readUser(Long id);
    void updateUser(Long id, String name, String surname, String login, String password, Gender gender);
    boolean deleteUser(Long id);
    User[] getAllUsers();
    User findByLogin(String login);
}
