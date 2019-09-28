package dev.ulman.dmsapi.dao;

import dev.ulman.dmsapi.model.User;

import java.util.List;

public interface UserDao {

   List<User> getAllUsers();
   User getUserByUsername(String username);

   void addUser(User user);
   void updateUser(long id, User incomingUser);
   void deleteUser(long id);
}
