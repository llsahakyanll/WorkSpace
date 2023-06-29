package com.company.workspace.dao;

import com.company.workspace.entity.User;
import com.company.workspace.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByUser(User user);
    void deleteByUser(User user);
    boolean existsAllByUser(User user);
}
