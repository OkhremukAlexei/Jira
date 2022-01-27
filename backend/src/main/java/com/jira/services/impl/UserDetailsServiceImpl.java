package com.jira.services.impl;

import com.jira.models.User;
import com.jira.repos.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER= LoggerFactory.getLogger(UserDetailsServiceImpl.class);


    @Autowired
    UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        LOGGER.info("UserDetailsServiceImpl method loadUserByUsername "+ login);

        User user = userRepo.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + login));
        return UserDetailsImpl.build(user);
    }

    @Transactional(readOnly = true)
    public User getCurrentUser(){
        LOGGER.info("UserDetailsServiceImpl method getCurrentUser ");

        UserDetails principal = (UserDetails) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepo.findByLogin(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }

}
