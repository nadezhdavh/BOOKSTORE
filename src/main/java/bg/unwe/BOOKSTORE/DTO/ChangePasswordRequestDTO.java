package bg.unwe.BOOKSTORE.DTO;

public class ChangePasswordRequestDTO
{
  private String password;
  private String oldPassword;
  private String passwordConfirmation;

  public ChangePasswordRequestDTO(String password, String oldPassword, String passwordConfirmation)
  {
    this.password = password;
    this.oldPassword = oldPassword;
    this.passwordConfirmation = passwordConfirmation;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getOldPassword()
  {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword)
  {
    this.oldPassword = oldPassword;
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
