package com.company.workspace.service.userDetails;


import com.company.workspace.entity.User;
import com.company.workspace.entity.UserDetails;

public interface UserDetailsService {
    UserDetails findByUser(User user);
    UserDetails findById(Long id);
    void deleteByUser(User user);
    boolean existsAllByUser(User user);
    void saveUserDetails(UserDetails userDetails);
    UserDetails createUserDetails();
}
