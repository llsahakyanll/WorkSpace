package com.company.workspace.service.userDetails;

import com.company.workspace.dao.UserDetailsRepository;
import com.company.workspace.entity.UserDetails;
import com.company.workspace.handler.UserRegistrationException;
import com.company.workspace.service.date.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UserDetailsRepository userDetailsRepository;
    private final DateService dateService;

    @Override
    public UserDetails findById(Long id) {
        return userDetailsRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveUserDetails(UserDetails userDetails) {
        String date = dateService.askDate();
        System.out.println(date);
        userDetails.setRegistrationDate(date);
        userDetailsRepository.save(userDetails);
    }

    @Override
    public void save(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }

    @Override
    public void checkUserDetails(UserDetails userDetails) {
        if (userDetailsRepository.existsByPhoneNumber(userDetails.getPhoneNumber()))
            throw new UserRegistrationException("User with the same phone number already exists.");
    }

    @Override
    public UserDetails createUserDetails() {
        return new UserDetails();
    }
}
