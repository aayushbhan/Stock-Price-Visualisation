package com.Visualizer.Stockopedia.Controller;

import com.Visualizer.Stockopedia.Service.RepositoryServices.User.UserServiceImplementation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public/users")
@AllArgsConstructor()
final class PublicUserController {

    @Autowired
    UserServiceImplementation userService;

    @PostMapping("/register")
    String register(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {

        return userService
                .createUser(
                        username, password
                ).orElseThrow( () -> new RuntimeException("Error in registration"));

    }

    @PostMapping("/login")
    String login(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {
        return userService
                .login(username, password)
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }
}
