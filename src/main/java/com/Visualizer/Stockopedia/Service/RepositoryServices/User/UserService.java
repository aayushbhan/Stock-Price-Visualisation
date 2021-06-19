package com.Visualizer.Stockopedia.Service.RepositoryServices.User;

import com.Visualizer.Stockopedia.Model.User;

import java.util.Optional;

public interface UserService {

    Optional<String> createUser(String username, String password);
    Optional<User> updateUsername(String userId,String newUsername);

    Optional<User> updatePassword(String userId, String newPassword);
    void deleteById(String userId);

    Optional<String> login(String username, String password);
    Optional<User> findByToken(String token);
    User findById(String id);
}
