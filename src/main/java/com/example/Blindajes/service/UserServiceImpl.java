package com.example.Blindajes.service;

import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.User;
import com.example.Blindajes.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> findAllUsers() throws ResourceNotFoundException {
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty()){
            log.error("Fail to list Users: Users data is empty");
            throw new  ResourceNotFoundException("Users data is empty");
        }
        return userList;
    }

    @Override
    public User findUserById(Long id) throws ResourceNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            return  userOptional.get();
        }else {
            log.error("Error finding user by ID: There is no user with the provided ID");
            throw new ResourceNotFoundException("The user cannot be found by the provided ID");
        }
    }

    @Override
    public void deleteUserById(Long id) throws ResourceNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(userRepository::delete);
        if (userOptional.isEmpty()){
            log.error("Failed to delete User: The user could not be found by the id provided");
            throw new ResourceNotFoundException("The user doesn't exists");
        }
    }

    @Override
    public User updateUser(User user) throws ResourceNotFoundException {
        if(!userRepository.existsById(user.getId())) {
            log.error("Fail to update User: User not found");
            throw new ResourceNotFoundException("The user trying to update doesn't exist");
        }
        User userToUpdate = userRepository.save(user);
        log.info("User updates successfully");
        return userToUpdate;
    }

    @Override
    public void disableUser(Long id) throws ResourceNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            user.setEnabled(!user.isEnabled());
            userRepository.save(user);
            log.info("Enabled for user has been modified correctly");
        } else {
            log.error("Fail to update User: User not found");
            throw new ResourceNotFoundException("there are no users with the provided id");
        }
    }

    public Optional<User> findUserByUsername(String username) throws ResourceNotFoundException{
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if(userOptional.isEmpty()){
            log.error("Fail to find User: User not found");
        }
        return userOptional;
    }
}
