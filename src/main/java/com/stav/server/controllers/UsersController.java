package com.stav.server.controllers;

import com.stav.server.beans.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @PostMapping
    public void createUser(@RequestBody User user) {
        System.out.println(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        System.out.println(user);
    }

    @GetMapping("{userId}")
    public User getUserById(@PathVariable("userId") int id) {
        return null;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return null;
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable("userId") int id) {

    }
}
