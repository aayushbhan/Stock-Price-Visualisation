package com.Visualizer.Stockopedia.Service.RepositoryServices.User;

import com.Visualizer.Stockopedia.Model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> createUser(User user);
    Optional<User> getUserById(String userId);
    Optional<User> updateUsername(String userId,String newUsername);
    Optional<User> updatePassword(String userId,String newPassword);
    Optional<User> deleteById(String userId);
}
