package com.example.Blindajes.controller;

import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.User;
import com.example.Blindajes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() throws ResourceNotFoundException{
        List<User> userList = userService.findAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping
    public ResponseEntity<User> findUserById(@RequestParam(value = "id") Long id ) throws ResourceNotFoundException{
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserById(@RequestParam(value = "id") Long id) throws ResourceNotFoundException {
        userService.deleteUserById(id);
        return ResponseEntity.ok("The user with id " + id + " has been deleted");
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUserById(@RequestBody User user) throws ResourceNotFoundException{
        return ResponseEntity.ok(userService.updateUser(user));
    }
    @PutMapping("/ability")
    public ResponseEntity<String> abilityUser(@RequestParam(value = "id") Long id) throws ResourceNotFoundException{
        userService.disableUser(id);
        return ResponseEntity.ok("the users ability has been modified");
    }

}
