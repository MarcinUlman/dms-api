package dev.ulman.dms.service;

import dev.ulman.dms.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void addUser(User user);
    User getUserByUsername(String username);

}
