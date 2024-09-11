package com.examportal.portal.service.impl;

import com.examportal.portal.model.User;
import com.examportal.portal.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

User user =this.userRepository.findByUserName(username);
        if(user==null){
            System.out.print("user not found");
            throw new UsernameNotFoundException("no user found !!");
        }
        return user;
    }

}
