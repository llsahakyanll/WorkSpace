package com.company.workspace.service.user;

import com.company.workspace.entity.User;
import com.company.workspace.entity.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    void saveUser(User user);
    void save(User user);
    User findById(Long id);
    void setUserDetails(User user, UserDetails userDetails);
    User createUser();

    void checkUser(User user);
}