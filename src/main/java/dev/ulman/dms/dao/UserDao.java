package dev.ulman.dms.dao;

import dev.ulman.dms.model.User;

import java.util.List;

public interface UserDao {

   List<User> getAllUsers();
   User getUserByUsername(String username);

   void addUser(User user);
   void updateUser(long id, User incomingUser);
   void deleteUser(long id);
}
