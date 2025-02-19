package com.vishwaravi.ciboseat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vishwaravi.ciboseat.repositories.WaistaffRepo;
/**
 * Custom Service Class that implements the UserDetails Service
 * for Spring Security Configuration.
 */
@Service("CustomUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    WaistaffRepo waistaffRepo;

    @Override
    public UserDetails loadUserByUsername(String username){
        var staffStatus = waistaffRepo.findByName(username);
        if (staffStatus.isPresent())
        return staffStatus.get();
        throw new UsernameNotFoundException("WaitStaff not Found");
    }
    
}
