package com.company.workspace.service.userDetails;

import com.company.workspace.dao.UserDetailsRepository;
import com.company.workspace.entity.User;
import com.company.workspace.entity.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails findByUser(User user) {
        return userDetailsRepository.findByUser(user);
    }

    @Override
    public UserDetails findById(Long id) {
        return userDetailsRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteByUser(User user) {
        userDetailsRepository.deleteByUser(user);
    }

    @Override
    public boolean existsAllByUser(User user) {
        return userDetailsRepository.existsAllByUser(user);
    }

    @Override
    public void saveUserDetails(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }

    @Override
    public UserDetails createUserDetails() {
        return new UserDetails();
    }
}
