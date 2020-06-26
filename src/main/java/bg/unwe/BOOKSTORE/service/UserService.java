package bg.unwe.BOOKSTORE.service;

import bg.unwe.BOOKSTORE.model.User;

import java.util.List;

public interface UserService
{
  User getUser(String username);

  void saveUser(User user);

  void deleteUser(String username);

  List<User> getUsers();

  User getUser(Integer id);

  void updateUser(User user);

  boolean changePassword(String username, String oldPassword, String newPassword);
}
