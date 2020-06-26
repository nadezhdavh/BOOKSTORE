package bg.unwe.BOOKSTORE.DTO;

import bg.unwe.BOOKSTORE.model.User;

public class UserDetailsDTO
{
  private String    username;
  private Boolean enabled;
  private User.Role role;

  public UserDetailsDTO()
  {
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public Boolean getEnabled()
  {
    return enabled;
  }

  public void setEnabled(Boolean enabled)
  {
    this.enabled = enabled;
  }

  public User.Role getRole()
  {
    return role;
  }

  public void setRole(User.Role role)
  {
    this.role = role;
  }
}
