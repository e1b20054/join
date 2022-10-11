package join.team9.join.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class Sample3AuthConfiguration {

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    UserBuilder users = User.builder();

    UserDetails user1 = users
        .username("user1")
        .password("$2y$10$9dWh5RpU05HhUJNdBFFQduiBvdRdYNddO2ugD8nY2XcABCKuw7ZZ.")
        .roles("USER")
        .build();
    UserDetails admin = users
        .username("admin")
        .password("$2y$10$/6Jv61KWt8A2ZT8mkOZwyO7W.4JyCWj2yRoOl9cZ7ACzC3Ku8JUT6")
        .roles("ADMIN")
        .build();
    return new InMemoryUserDetailsManager(user1, admin);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.formLogin();

    http.authorizeHttpRequests()
        .mvcMatchers("/sample3/**").authenticated();

    // http.authorizeHttpRequests()
    // .mvcMatchers("/sample3/**").permitAll();

    http.logout().logoutSuccessUrl("/");
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
