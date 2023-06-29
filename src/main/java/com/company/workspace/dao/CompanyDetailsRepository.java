package com.company.workspace.dao;

import com.company.workspace.entity.CompanyDetails;
import com.company.workspace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDetailsRepository extends JpaRepository<CompanyDetails, Long> {
    CompanyDetails findByUser(User user);
    void deleteByUser(User user);
    boolean existsAllByUser(User user);
}
