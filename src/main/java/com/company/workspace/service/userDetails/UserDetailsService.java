package com.company.workspace.service.userDetails;

import com.company.workspace.entity.UserDetails;

public interface UserDetailsService {
    UserDetails findById(Long id);
    void saveUserDetails(UserDetails userDetails);
    void save(UserDetails userDetails);
    void checkUserDetails(UserDetails userDetails);
    UserDetails createUserDetails();
}
