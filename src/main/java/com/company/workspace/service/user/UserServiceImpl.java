package com.company.workspace.service.user;


import com.company.workspace.dao.AuthorityRepository;
import com.company.workspace.dao.UserRepository;
import com.company.workspace.entity.Authority;
import com.company.workspace.entity.User;
import com.company.workspace.handler.UserRegistrationException;
import com.company.workspace.service.date.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements  UserService{
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final DateService dateService;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    // Adding Authority And Setting Enabled
    public void saveUser(User user) {
        System.out.println("saveUser Method");
        checkUser(user);
        user.setEnabled(true);
        List<Authority> list = new ArrayList<>();
        Authority authority = authorityRepository.findById(1L).orElse(null);
        list.add(authority);
        user.setAuthorities(list);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void setUserDetails(User user, com.company.workspace.entity.UserDetails userDetails) {
        user.setUserDetails(userDetails);
    }

    @Override
    public User createUser() {
        return new User();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAuthority(user.getAuthorities())
        );
    }

    private Set<SimpleGrantedAuthority> getAuthority(List<Authority> roles) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Authority role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public void checkUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new UserRegistrationException("User with the same email already exists.");
    }
}