package bg.unwe.BOOKSTORE.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserRegistrationDTO
{

  @NotEmpty
  @Pattern(regexp = "^[a-zA-Z0-9._\\-]{3,16}$")
  private String username;
  private String password;
  private String passwordConfirmation;

  public UserRegistrationDTO()
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

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getPasswordConfirmation()
  {
    return passwordConfirmation;
  }

  public void setPasswordConfirmation(String passwordConfirmation)
  {
    this.passwordConfirmation = passwordConfirmation;
  }
}
