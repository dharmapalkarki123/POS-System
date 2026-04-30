package com.pos.service;

import com.pos.exception.UserException;
import com.pos.modal.Users;

import java.util.List;

public interface UserService {

    Users getUserFromJwtToken(String token) throws UserException;
    Users getCurrentUser() throws UserException;
    Users getUserByEmail(String email) throws UserException;
    Users  getUserById(Long id) throws UserException;
    List<Users> getAllUsers() throws UserException;
}
