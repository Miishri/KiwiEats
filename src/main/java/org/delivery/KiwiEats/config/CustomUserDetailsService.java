package org.delivery.KiwiEats.config;

import org.aspectj.weaver.ast.Not;
import org.delivery.KiwiEats.entities.roles.User;
import org.delivery.KiwiEats.exception.NotFoundException;
import org.delivery.KiwiEats.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found with Username: " + username));

        return new CustomUserDetails(user);
    }


}
