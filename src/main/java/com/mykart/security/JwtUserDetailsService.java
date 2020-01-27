package com.mykart.security;

import com.mykart.model.Authentication;
import com.mykart.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	@Autowired
	private AuthenticationService authenticationService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if ("admin".equals(username)) {
      return new User(
          "admin",
          "$2y$12$ZvI8ySzJwMfUkGcvn5noQO/a5LknqHkf9sjnhjhgBoguwGCQRSJyW",
          new ArrayList<>());
    } else {
      Authentication auth = authenticationService.getUserByUsername(username);
      final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

      if (auth != null) {
        String encodedPassword = passwordEncoder.encode(auth.getPassword());
        return new User(auth.getUsername(), encodedPassword, new ArrayList<>());
      } else throw new UsernameNotFoundException(" User Not Found");
    }
		}
}
