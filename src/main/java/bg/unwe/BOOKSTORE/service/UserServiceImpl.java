package bg.unwe.BOOKSTORE.service;

import bg.unwe.BOOKSTORE.model.User;
import bg.unwe.BOOKSTORE.reopository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
  @Autowired
  UserRepository userRepository;

  @Autowired
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder)
  {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User getUser(String username)
  {
    return userRepository.getUser (username);
  }

  @Override
  public void saveUser(User user)
  {
    userRepository.saveUser (user);
  }

  @Override
  public void deleteUser(String username)
  {
    userRepository.deleteUser (username);
  }

  @Override
  public List<User> getUsers()
  {
    return userRepository.getUsers ();
  }

  @Override
  public User getUser(Integer id)
  {
    return userRepository.getUser (id);
  }

  @Override
  public void updateUser(User user)
  {
    userRepository.updateUser (user);
  }

  @Override
  public boolean changePassword(String username, String oldPassword, String newPassword)
  {
    User user = getUser (username);
    if (oldPassword.equals (newPassword)) {
      return false;
    }
    if (passwordEncoder.matches (oldPassword, user.getPassword ())) {
      user.setPassword (passwordEncoder.encode (newPassword));
      userRepository.updateUser (user);
      return true;
    }
    return false;
  }
}
