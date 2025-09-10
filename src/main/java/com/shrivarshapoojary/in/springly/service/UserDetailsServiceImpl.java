package com.shrivarshapoojary.in.springly.service;

import com.shrivarshapoojary.in.springly.models.User;
import com.shrivarshapoojary.in.springly.repository.UserRepository;
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
        User user=userRepository.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("User Not found"));

        return UserDetailsImpl.build(user);

    }
}
