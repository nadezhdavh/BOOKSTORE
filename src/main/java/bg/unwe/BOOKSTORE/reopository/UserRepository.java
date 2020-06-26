package bg.unwe.BOOKSTORE.reopository;


import bg.unwe.BOOKSTORE.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository
{
  User getUser(String username);

  void saveUser(User user);

  void deleteUser(String username);

  List<User> getUsers();

  User getUser(Integer id);

  void updateUser(User user);
}
