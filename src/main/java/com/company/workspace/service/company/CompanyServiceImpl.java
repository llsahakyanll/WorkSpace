package com.company.workspace.service.company;

import com.company.workspace.dao.CompanyDetailsRepository;
import com.company.workspace.entity.CompanyDetails;
import com.company.workspace.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService{

    private final CompanyDetailsRepository companyDetailsRepository;

    @Override
    public CompanyDetails findByUser(User user) {
        return companyDetailsRepository.findByUser(user);
    }

    @Override
    public CompanyDetails findById(Long id) {
        return companyDetailsRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteByUser(User user) {
        companyDetailsRepository.deleteByUser(user);
    }

    @Override
    public boolean existsAllByUser(User user) {
        return companyDetailsRepository.existsAllByUser(user);
    }

    @Override
    public void save(CompanyDetails companyDetails) {
        companyDetailsRepository.save(companyDetails);
    }
}
