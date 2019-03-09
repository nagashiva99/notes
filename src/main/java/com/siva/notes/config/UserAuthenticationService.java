package com.siva.notes.config;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.siva.notes.model.User;
import com.siva.notes.model.UserAuthentication;
import com.siva.notes.repository.UserRepository;

@Service
public class UserAuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUserNameOrEmailAllIgnoreCase(username, username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
        Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);
        //return new UserAuthentication(user, authorities);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }

}
