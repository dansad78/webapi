package com.example.webapi.service;

import com.example.webapi.dao.ApiUserDao;
import com.example.webapi.entity.ApiUser;
import com.example.webapi.entity.ApiUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private ApiUserDao apiUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApiUser user = null;
        try{
            user = apiUserDao.findFirstByUsername(username);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(user != null){
             return new ApiUserDetails(user);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

    }
}