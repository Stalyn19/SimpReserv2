package com.simpreserv.service;

import com.simpreserv.model.User;
import com.simpreserv.repository.UserRepository;
import com.simpreserv.repository.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);
    if (user==null){
      throw new UsernameNotFoundException("User not found");
    }
    return new CustomUserDetails(user);
  }
}
