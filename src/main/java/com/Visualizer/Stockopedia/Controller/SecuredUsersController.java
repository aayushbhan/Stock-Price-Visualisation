package com.Visualizer.Stockopedia.Controller;

import com.Visualizer.Stockopedia.Model.User;
import com.Visualizer.Stockopedia.Service.RepositoryServices.User.UserServiceImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/users")
final class SecuredUsersController {

    private final UserServiceImplementation userService;

    public SecuredUsersController(UserServiceImplementation userService) {
        this.userService = userService;
    }

    @GetMapping("/current")
    User getCurrent(@AuthenticationPrincipal final User user) {
        return user;
    }

    @GetMapping("/logout")
    boolean logout(@AuthenticationPrincipal final User user) {
        userService.logout(user);
        return true;
    }


    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId")String userId){
        userService.deleteById(userId);
        return ResponseEntity.ok("User Deleted");
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId")String userId,
                                             @RequestBody User user){

        userService.updateUser(userId,user.getUsername(),user.getPassword());

        return ResponseEntity.ok("User updated successfully");
    }


}