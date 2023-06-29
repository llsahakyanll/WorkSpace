package com.company.workspace.service.company;

import com.company.workspace.entity.CompanyDetails;
import com.company.workspace.entity.User;

public interface CompanyService {
    CompanyDetails findByUser(User user);
    CompanyDetails findById(Long id);
    void deleteByUser(User user);
    boolean existsAllByUser(User user);
    void save(CompanyDetails companyDetails);
}
