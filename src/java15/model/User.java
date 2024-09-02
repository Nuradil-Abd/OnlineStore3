package java15.model;

import java15.enums.Gender;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Gender gender;

    public User(Long id, String name, String surname, String login, String password, Gender gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User(" +
                "id: " + id +
                ", name: '" + name + '\'' +
                ", surname: '" + surname + '\'' +
                ", login: '" + login + '\'' +
                ", password: '" + password + '\'' +
                ", gender: " + gender +
                ')';
    }
}
