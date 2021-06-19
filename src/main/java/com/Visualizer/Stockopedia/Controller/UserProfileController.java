package com.Visualizer.Stockopedia.Controller;

import com.Visualizer.Stockopedia.Model.User;
import com.Visualizer.Stockopedia.Service.RepositoryServices.User.UserServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserProfileController {

    private final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    private final UserServiceImplementation userService;

    public UserProfileController(UserServiceImplementation userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public User getUserDetail(@PathVariable String id){
        User user = userService.findById(id);
        logger.info("$$$$"+user.getUsername());
        return user;
    }


}