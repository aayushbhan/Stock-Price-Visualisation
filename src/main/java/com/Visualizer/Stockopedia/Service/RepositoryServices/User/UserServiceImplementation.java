package com.Visualizer.Stockopedia.Service.RepositoryServices.User;


import com.Visualizer.Stockopedia.Model.User;

import java.util.Optional;

public class UserServiceImplementation implements UserService{

    @Override
    public Optional<User> createUser(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> updateUsername(String userId, String newUsername) {
        return Optional.empty();
    }

    @Override
    public Optional<User> updatePassword(String userId, String newPassword) {
        return Optional.empty();
    }

    @Override
    public Optional<User> deleteById(String userId) {
        return Optional.empty();
    }
}
