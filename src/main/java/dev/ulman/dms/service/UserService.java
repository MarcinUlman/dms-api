package dev.ulman.dms.service;

import dev.ulman.dms.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserByUsername(String username);

    void addUser(User user);
    void updateUser(long id, User incomingUser);
    void deleteUser(long id);

    // TODO Reset Your Password
    // -> https://www.baeldung.com/spring-security-registration-i-forgot-my-passwor

    // TODO Change Your Password
}
