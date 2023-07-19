package by.eshop.service;

import by.eshop.model.User;
public interface UserService {

    void addUser(User user);

    User getUserByLogin(String login, String password);

    boolean checkUser(String email, String password);
}