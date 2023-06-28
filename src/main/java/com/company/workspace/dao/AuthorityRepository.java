package com.company.workspace.dao;

import com.company.workspace.entity.Authority;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    Authority findByName(String name);
    @NotNull Optional<Authority> findById(@NotNull Long id);
}
