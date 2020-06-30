package bg.unwe.BOOKSTORE.config;

import bg.unwe.BOOKSTORE.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static bg.unwe.BOOKSTORE.model.User.Role;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
  @Autowired
  DataSource dataSource;

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http
        .authorizeRequests ()
        .antMatchers (HttpMethod.GET, "/api/v1/books").hasAnyAuthority (User.Role.USER.toString (),Role.ADMIN.toString ())
        .antMatchers (HttpMethod.GET, "/api/v1/books/**").hasAnyAuthority (User.Role.USER.toString (),Role.ADMIN.toString ())
        .antMatchers (HttpMethod.POST, "/api/v1/books/new").hasAuthority (User.Role.ADMIN.toString ())
        .antMatchers (HttpMethod.POST, "/api/v1/books/{/[^0-9]}/price").hasAuthority (User.Role.ADMIN.toString ())
        .antMatchers (HttpMethod.DELETE, "/api/v1/books/**").hasAuthority (User.Role.ADMIN.toString ())
        .antMatchers (HttpMethod.POST, "/api/v1/books/{/[^0-9]}/buy").hasAnyAuthority (User.Role.ADMIN.toString (), Role.USER.toString ())
        .antMatchers (HttpMethod.GET, "/api/v1/ratings/**").hasAnyAuthority (User.Role.USER.toString (), User.Role.ADMIN.toString ())
        .antMatchers (HttpMethod.POST, "/api/v1/ratings/**").hasAnyAuthority (User.Role.USER.toString (), User.Role.ADMIN.toString ())

        .antMatchers (HttpMethod.GET, "/api/v1/users/all").hasAuthority (User.Role.ADMIN.toString ())
        .antMatchers (HttpMethod.GET, "/api/v1/users/me").hasAnyAuthority (User.Role.ADMIN.toString (), User.Role.USER.toString ())
        .antMatchers (HttpMethod.POST, "/api/v1/users/pass").hasAnyAuthority (User.Role.USER.toString (), User.Role.ADMIN.toString ())
        .antMatchers (HttpMethod.DELETE, "/api/v1/users/**").hasAuthority (User.Role.ADMIN.toString ())
        .antMatchers (HttpMethod.GET, "/api/v1/activate/**").hasAuthority (User.Role.ADMIN.toString ())
        .antMatchers (HttpMethod.POST, "/api/v1/register").permitAll ()

        .and ()
        .httpBasic ();


    http.csrf ().disable ();
    http.headers ().frameOptions ().disable ();

  }

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {

  auth.jdbcAuthentication().dataSource(dataSource)
      .usersByUsernameQuery("select username, password, enabled"
          + " from users where username=?")
      .authoritiesByUsernameQuery("select username, role  "
          + "from users where username=?");
}

  @Bean
  public PasswordEncoder passwordEncoder()
  {
    return new BCryptPasswordEncoder ();
  }

}
