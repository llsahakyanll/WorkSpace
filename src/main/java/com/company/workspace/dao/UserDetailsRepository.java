package com.company.workspace.dao;

import com.company.workspace.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
