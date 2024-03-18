package com.example.Blindajes.service;

import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers() throws ResourceNotFoundException;
    User findUserById(Long id) throws ResourceNotFoundException;
    void deleteUserById (Long id) throws ResourceNotFoundException;
    User updateUser(User user) throws ResourceNotFoundException;

    void disableUser(Long id) throws ResourceNotFoundException;
}
