package com.pos.service.impl;

import com.pos.modal.Users;
import com.pos.repository.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserImplementation implements UserDetailsService {


    private final UsersRepository usersRepository;

    public CustomUserImplementation(UsersRepository usersRepository) {


        this.usersRepository = usersRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user=usersRepository.findByEmail(username);

        if(user==null){

            throw new UsernameNotFoundException("User not found");

        }


        GrantedAuthority authority=new SimpleGrantedAuthority(
                user.getRole().toString()
        );


        Collection<GrantedAuthority> authorities= Collections
                .singletonList(authority);

        return new User(user.getEmail(),
                user.getPassword(),authorities);


    }
}
