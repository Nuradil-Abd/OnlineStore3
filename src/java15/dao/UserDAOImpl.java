package java15.dao;

import java15.model.User;
import java15.enums.Gender;

public class UserDAOImpl implements UserDAO {
    private User[] users = new User[10];
    private int userCount = 0;
    private Long idCounter = 1L;


    @Override
    public User findByLogin(String login) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getLogin().equals(login)) {
                return users[i];
            }
        }
        return null;
    }



    @Override
    public User createUser(String name, String surname, String login, String password, Gender gender) {
        if (userCount == users.length) {
            resizeArray();
        }
        User user = new User(idCounter++, name, surname, login, password, gender);
        users[userCount++] = user;
        return user;
    }

    @Override
    public User readUser(Long id) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getId().equals(id)) {
                return users[i];
            }
        }
        return null;
    }

    @Override
    public void updateUser(Long id, String name, String surname, String login, String password, Gender gender) {
        User user = readUser(id);
        if (user != null) {
            user.setName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            user.setGender(gender);
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getId().equals(id)) {
                users[i] = users[userCount - 1];
                users[userCount - 1] = null;
                userCount--;
                return true;
            }
        }
        return false;
    }

    @Override
    public User[] getAllUsers() {
        User[] result = new User[userCount];
        System.arraycopy(users, 0, result, 0, userCount);
        return result;
    }



    private void resizeArray() {
        User[] newArray = new User[users.length * 2];
        System.arraycopy(users, 0, newArray, 0, users.length);
        users = newArray;
    }
}
