package bg.unwe.BOOKSTORE.reopository;

import bg.unwe.BOOKSTORE.model.User;
import oracle.jdbc.OracleDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

public class UserRepositoryImplTest
{
  @Autowired
  private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder ;
  @Autowired
  UserRepository                                               userRepository;

  public UserRepositoryImplTest(org.springframework.security.crypto.password.PasswordEncoder passwordEncoder,  UserRepository userRepository)
  {
    this.passwordEncoder = passwordEncoder;
    this.userRepository =userRepository;
  }

  @BeforeMethod
  public void setUp()
  {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource ();
    dataSource.setUrl ("jdbc:oracle:thin:@//83.228.124.173:6223/nadezhda_hristeva");
    dataSource.setUsername ("nadezhda_hristeva");
    dataSource.setPassword ("dbpass");
    dataSource.setDriverClass (OracleDriver.class);
    userRepository = new UserRepositoryImpl (new JdbcTemplate (dataSource));
  }

  @Test
  public void testGetUser()
  {
    Integer id = 1111;
    User user = userRepository.getUser (id);
    assertNotNull (user);

  }

  @Test
  public void testSaveUser()
  {
    User user = new User();
    user.setUsername ("admin");
    user.setEnabled (Boolean.TRUE);
    user.setPassword (passwordEncoder.encode ("pass"));
    user.setRole (User.Role.valueOf ("ADMIN"));
    userRepository.saveUser (user);
  }

  @Test
  public void testDeleteUser()
  {
    String username ="user";
    userRepository.deleteUser (username);
  }

  @Test
  public void testGetUsers()
  {
    List<User> list = userRepository.getUsers ();
    assertFalse (list.isEmpty ());
  }

  @Test
  public void testTestGetUser()
  {
    String username = "admin";
    User user = userRepository.getUser (username);
    assertNotNull (user);
  }

  @Test
  public void testUpdateUser()
  {
    User user = userRepository.getUser (170);
    user.setRole (User.Role.ADMIN);
    userRepository.updateUser (user);
  }
}