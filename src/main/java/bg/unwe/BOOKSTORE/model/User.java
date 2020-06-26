package bg.unwe.BOOKSTORE.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "USER_SEQ", initialValue = 10, allocationSize = 1)
public class User
{
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
  private Integer   Id;
  @NotNull
  @Column(name = "username", unique = true, nullable = false)
  @NotNull
  @Size(min = 2, max = 40)
  @Pattern(regexp = "^[a-z0-8.\\-]+$")
  private String    username;
  @NotNull
  @Size(min = 5, max = 90)
  @NotNull
  @Column(name = "password", nullable = false)
  private String    password;
  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private Role   role;
  private Boolean enabled;

  public User()
  {
  }

  public User(@NotNull @NotNull @Size(min = 2, max = 40) @Pattern(regexp = "^[a-z0-8.\\-]+$") String username, @NotNull String password, Role role)
  {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public Integer getId()
  {
    return Id;
  }

  public void setId(Integer id)
  {
    Id = id;
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

  public Role getRole()
  {
    return role;
  }

  public void setRole(Role role)
  {
    this.role = role;
  }

  public Boolean getEnabled()
  {
    return enabled;
  }

  public void setEnabled(Boolean enabled)
  {
    this.enabled = enabled;
  }
  public enum Role
  {
    ADMIN, USER;
  }
}
