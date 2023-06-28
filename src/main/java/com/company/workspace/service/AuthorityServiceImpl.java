package com.company.workspace.service;

import com.company.workspace.dao.AuthorityRepository;
import com.company.workspace.entity.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService{

    private final AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public void saveAuthority(Authority authority) {
        authorityRepository.save(authority);
    }

    @Override
    @Transactional
    public Authority findByName(String name) {
        return authorityRepository.findByName(name);
    }

    @Override
    public Authority findById(Long id) {
        return authorityRepository.findById(id).orElse(null);
    }
}
