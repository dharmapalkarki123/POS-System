package com.pos.service.impl;

import com.pos.configuration.JwtProvider;
import com.pos.exception.UserException;
import com.pos.modal.Users;
import com.pos.repository.UsersRepository;
import com.pos.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final JwtProvider jwtProvider;

    public UserServiceImpl(UsersRepository usersRepository, JwtProvider jwtProvider) {
        this.usersRepository = usersRepository;
        this.jwtProvider =jwtProvider;
    }

    @Override
    public Users getUserFromJwtToken(String token) throws UserException {

        String email=jwtProvider.getEmailFromToken(token);

        Users user=usersRepository.findByEmail(email);
        if(user==null) {
            throw new UserException("Invalid token");
        }
        return user;
    }

    @Override
    public Users getCurrentUser() throws UserException {
        String email= SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Users user=usersRepository.findByEmail(email);
        if(user==null)
            throw new UserException("User not found");
        return user;
    }

    @Override
    public Users getUserByEmail(String email) throws UserException {
        Users user=usersRepository.findByEmail(email);
        if(user==null)
            throw new UserException("User not found");
        return user;
    }

    @Override
    public Users getUserById(Long id) throws UserException {
        return usersRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Users> getAllUsers() throws UserException {
        return usersRepository.findAll();
    }
}
