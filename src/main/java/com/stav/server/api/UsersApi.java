package com.stav.server.api;

import java.util.ArrayList;
import java.util.List;

import com.stav.server.beans.User;
import com.stav.server.beans.UserLoginDetails;
import com.stav.server.enums.UserType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class UsersApi {

    //  URL : http://localhost:8080/users/login
    @PostMapping("/login")
    public void login(@RequestBody UserLoginDetails userLoginDetails ) {
        System.out.println(userLoginDetails);
    }

    // ---------------------------
    // CRUD
    // -------------------------

    //  URL : http://localhost:8080/users
    @PostMapping
    public void createUser(@RequestBody User user) {
        System.out.println(user);
    }

    //  URL : http://localhost:8080/users
    @PutMapping
    public void updateUser(@RequestBody User user) {
        System.out.println(user);
    }

    // http://localhost:8080/users/12
    @GetMapping("{userId}")
    public User getUser(@PathVariable("userId") long id) {
        System.out.println(id);

        User mockUser = new User("Avi", "1234", UserType.Admin, 123);
        return mockUser;
    }

    // http://localhost:8080/users/12
    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable("userId") long id) {
        System.out.println(id + "has been deleted");
    }


    //  URL : http://localhost:8080/users
    @GetMapping
    public List<User> getAllUsers(){
        User mockUser1 = new User("Avi", "1234", UserType.Admin, 123);
        User mockUser2 = new User("Danny", "ffffsss", UserType.Customer, 123);
        User mockUser3 = new User("Eli", "123aavv4", UserType.Company, 123);

        List<User> users = new ArrayList();
        users.add(mockUser1);
        users.add(mockUser2);
        users.add(mockUser3);

        return users;
    }


    // URL : http://localhost:8080/users/byAge?age=18
    @DeleteMapping("/byAge")
    public void deleteUsersByAge(@RequestParam("age") int age) {
        System.out.println(age);
    }
}