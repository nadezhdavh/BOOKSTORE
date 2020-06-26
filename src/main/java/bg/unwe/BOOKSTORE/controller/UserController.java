package bg.unwe.BOOKSTORE.controller;

import bg.unwe.BOOKSTORE.DTO.ChangePasswordRequestDTO;
import bg.unwe.BOOKSTORE.DTO.UserDetailsDTO;
import bg.unwe.BOOKSTORE.DTO.UserRegistrationDTO;
import bg.unwe.BOOKSTORE.DTO.UserResponseDTO;
import bg.unwe.BOOKSTORE.model.User;
import bg.unwe.BOOKSTORE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1")
public class UserController
{
  private UserService userService;

  @Autowired
  private final PasswordEncoder passwordEncoder;

  public UserController(UserService userService, PasswordEncoder passwordEncoder)
  {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping(value = "/all")
  public List<UserResponseDTO> getUsers()
  {
    List<User> users = userService.getUsers ();
    List<UserResponseDTO> userList = new ArrayList<> ();
    for (User user : users) {
      UserResponseDTO userDTO = new UserResponseDTO ();
      userDTO.setUsername (user.getUsername ());
      userList.add (userDTO);
    }
    return userList;
  }

  @GetMapping(value = "/users/me")
  public ResponseEntity<UserDetailsDTO> getUser()
  {
    Principal loggedInUser = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.getUser (loggedInUser.getName ());
    UserDetailsDTO userDTO = new UserDetailsDTO ();
    userDTO.setUsername (user.getUsername ());
    userDTO.setEnabled (user.getEnabled ());
    userDTO.setRole (user.getRole ());
    return ResponseEntity.ok (userDTO);
  }

  @PostMapping(value = "/users/pass")
  public ResponseEntity<ChangePasswordRequestDTO> changePassword( @Valid @RequestBody ChangePasswordRequestDTO changePassword)
  {
    Principal loggedInUser = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.getUser (loggedInUser.getName ());
    if (changePassword.getPassword ().equals (changePassword.getPasswordConfirmation ())) {

        userService.changePassword (user.getUsername (), changePassword.getOldPassword (),changePassword.getPassword ());
        return ResponseEntity.ok ().build ();
    }
    return ResponseEntity.badRequest ().build ();
  }

  @DeleteMapping(value = "/{user}")
  public ResponseEntity<?> deleteUser(@PathVariable("user") String username)
  {
    userService.deleteUser (username);
    return ResponseEntity.ok ().build ();
  }

  @PostMapping(value = "/register")
  public ResponseEntity<Void> register(@Valid @RequestBody UserRegistrationDTO userRegistration)
  {
    if (userRegistration.getPassword ().equals (userRegistration.getPasswordConfirmation ())) {
      User user = new User ();
      user.setUsername (userRegistration.getUsername ());
      user.setPassword (passwordEncoder.encode (userRegistration.getPassword ()));
      user.setEnabled (Boolean.FALSE);
      user.setRole (User.Role.USER);
      userService.saveUser (user);
      return ResponseEntity.ok ().build ();
    }
    else {
      return ResponseEntity.badRequest ().build ();
    }
  }

  @GetMapping(value = "/activate/{user}")
  public ResponseEntity<Void> activate(@PathVariable("user") String username)
  {
    User user = userService.getUser (username);
    user.setEnabled (Boolean.TRUE);
    userService.updateUser (user);
    return ResponseEntity.ok ().build ();
  }
}
