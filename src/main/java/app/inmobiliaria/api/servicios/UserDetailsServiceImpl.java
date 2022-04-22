package app.inmobiliaria.api.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.inmobiliaria.api.entity.User;
import app.inmobiliaria.api.repository.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }
  
  @Transactional
  public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + id));
	        return UserDetailsImpl.build(user);
  }

}
