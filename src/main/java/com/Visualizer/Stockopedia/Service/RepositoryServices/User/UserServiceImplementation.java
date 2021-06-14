package com.Visualizer.Stockopedia.Service.RepositoryServices.User;


import com.Visualizer.Stockopedia.Model.User;
import com.Visualizer.Stockopedia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> createUser(User user) {

        //Look for a user with the same username that exists
        Optional<User> user1 = userRepository.findById(user.getUserId());

        if(user1.isPresent()){
            return null;
        }

        User user2 = userRepository.insert(user);

        return Optional.of(user2);
    }

    @Override
    public Optional<User> updateUsername(String userId, String newUsername) {

        boolean hasSameUserName = userRepository.findAll()
                                                .stream()
                                                .anyMatch(u -> u.getUserId().equalsIgnoreCase(newUsername));

        if(hasSameUserName){
            return null;
        }

        User user1 = userRepository.findById(userId).get();

        user1.setUsername(newUsername);

        Optional<User> savedUid = Optional.of(userRepository.save(user1));

        return savedUid;
    }

    @Override
    public Optional<User> updatePassword(String userId, String newPassword) {

        boolean hasSamePassword = userRepository.findAll()
                                                .stream()
                                                .anyMatch(u -> u.getPassword().equalsIgnoreCase(newPassword));

        if(hasSamePassword){
            return null;
        }

        User user1 = userRepository.findById(userId).get();

        user1.setUsername(newPassword);

        return Optional.of(userRepository.save(user1));
    }

    @Override
    public void deleteById(String userId) {
        userRepository.deleteById(userId);
    }
}
