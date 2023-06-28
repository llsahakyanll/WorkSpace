package com.company.workspace.service;

import com.company.workspace.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User findByUsername(String username);
    void saveUser(User user);
    void save(User user);
    User findById(Long id);
    boolean checkUser(User user);
}

// APE INCH ?????? APE BAN !!!!!!!!