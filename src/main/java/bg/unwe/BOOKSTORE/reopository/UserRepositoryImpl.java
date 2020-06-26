package bg.unwe.BOOKSTORE.reopository;

import bg.unwe.BOOKSTORE.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository
{
  @Autowired
  private JdbcTemplate template;

  public UserRepositoryImpl(JdbcTemplate jdbcTemplate)
  {
    this.template = jdbcTemplate;
  }
  @Override
  public User getUser(String username)
  {
    String sql = "SELECT * FROM USERS WHERE username = ?";
    Object[] args = {username};
    User user = template.queryForObject (sql, args, BeanPropertyRowMapper.newInstance (User.class));
    return user;
  }

  @Override
  public void saveUser(User user)
  {
    String sql = "INSERT INTO USERS (id, enabled, password, role, username) VALUES (USER_SEQ.nextval, ?, ?, ?, ?)";
    template.update (sql, user.getEnabled (),  user.getPassword (), user.getRole ().toString (),
        user.getUsername ());
  }

  @Override
  public void deleteUser(String username)
  {
    String sql = "DELETE FROM USERS WHERE username = ?";
    template.update (sql, username);
  }

  @Override
  public List<User> getUsers()
  {
    String sql = "SELECT * FROM USERS";
    List<User> users = template.query (sql, BeanPropertyRowMapper.newInstance (User.class));
    return users;
  }

  @Override
  public User getUser(Integer id)
  {
    String sql = "SELECT * FROM USERS WHERE id = ?";
    Object[] args = {id};
    User user = template.queryForObject (sql, args, BeanPropertyRowMapper.newInstance (User.class));
    return user;
  }

  @Override
  public void updateUser(User user)
  {
    String sql = "UPDATE users SET enabled = ?, password = ?, username = ? WHERE id = ?";
    template.update(sql, user.getEnabled (), user.getPassword (),
        user.getUsername (), user.getId ());
  }
}
