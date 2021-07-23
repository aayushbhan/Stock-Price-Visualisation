package com.Visualizer.Stockopedia.Controller;

import com.Visualizer.Stockopedia.Service.RepositoryServices.User.UserServiceImplementation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public/users")
@CrossOrigin( origins = "http://localhost:3000" , maxAge = 3600)
final class PublicUserController {

    private final UserServiceImplementation userService;

    public PublicUserController(UserServiceImplementation userService) {
        this.userService = userService;
    }

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
                .get();
    }
}
