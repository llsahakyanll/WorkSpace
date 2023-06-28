package com.company.workspace.dao;

import com.company.workspace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phone);
}
