package com.hmfurtado.mongodb.controller;

import com.hmfurtado.mongodb.model.dto.UserDTO;
import com.hmfurtado.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> fetchUsers() {
        return userService.fetchUsers();
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String newUser(@RequestBody UserDTO user) {
        userService.newUser(user);
        return "User successfully created";
    }

    @DeleteMapping(value = "/user")
    public String deleteUser(@RequestBody String email) {
        userService.deleteUser(email);
        return "User successfully deleted";
    }

    @GetMapping(value = "/user")
    public UserDTO findUserByEmail(@RequestBody String email) {
        return userService.findUserByEmail(email);
    }
}
