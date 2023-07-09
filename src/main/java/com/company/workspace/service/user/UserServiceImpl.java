package com.company.workspace.service.user;


import com.company.workspace.dao.AuthorityRepository;
import com.company.workspace.dao.UserRepository;
import com.company.workspace.entity.Authority;
import com.company.workspace.entity.User;
import com.company.workspace.service.date.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final DateService dateService;

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        System.out.println("saveUser Method");
        user.setEnabled(true);
        List<Authority> list = new ArrayList<>();
        Authority authority = authorityRepository.findById(1L).orElse(null);
        list.add(authority);
        user.setAuthorities(list);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean checkUser(User user) {
        return !(userRepository.existsByEmail(user.getEmail()));
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
}