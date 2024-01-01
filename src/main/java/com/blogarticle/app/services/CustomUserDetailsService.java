package com.blogarticle.app.services;

import com.blogarticle.app.entities.CustomUserDetails;
import com.blogarticle.app.entities.User;
import com.blogarticle.app.exceptions.ResourceNotFoundException;
import com.blogarticle.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepo.findByEmail(username);
        if(!userOptional.isPresent())
            throw new ResourceNotFoundException("User","username",username);
        User user = userOptional.get();
        return new CustomUserDetails(user);
    }
}
