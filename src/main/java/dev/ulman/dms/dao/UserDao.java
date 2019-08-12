package dev.ulman.dms.dao;

import dev.ulman.dms.model.User;

public interface UserDao {
    User getUserByUsername(String username);

    void addUser(User user);
}
