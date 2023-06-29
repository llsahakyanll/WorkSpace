package com.company.workspace.service.authority;

import com.company.workspace.entity.Authority;

public interface AuthorityService {
    void saveAuthority(Authority authority);
    Authority findByName(String name);
    Authority findById(Long id);
}
