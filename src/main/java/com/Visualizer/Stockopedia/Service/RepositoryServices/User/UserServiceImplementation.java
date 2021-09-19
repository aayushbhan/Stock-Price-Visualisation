package com.Visualizer.Stockopedia.Service.RepositoryServices.User;


import com.Visualizer.Stockopedia.Model.User;
import com.Visualizer.Stockopedia.Repository.UserRepository;
import com.Visualizer.Stockopedia.Service.RepositoryServices.Portfolio.PortfolioService;
import com.Visualizer.Stockopedia.Service.RepositoryServices.Transaction.TransactionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;
    private final TransactionService transactionService;
    private final PortfolioService portfolioService;

    public UserServiceImplementation(UserRepository userRepository, TransactionService transactionService, PortfolioService portfolioService) {
        this.userRepository = userRepository;
        this.transactionService = transactionService;
        this.portfolioService = portfolioService;
    }

    @Override
    public Optional<String> createUser(String username, String password) {
        final String uuid = UUID.randomUUID().toString();
        final User user = User
                .builder()
                .token(uuid)
                .username(username)
                .password( new BCryptPasswordEncoder().encode(password))
                .build();

        userRepository.insert(user);
        return Optional.of(uuid);
    }

    @Override
    public Optional<User> updateUser(String userId, String newUsername, String password) {

        boolean hasSameUserName = userRepository.findAll()
                                                .stream()
                                                .anyMatch(u -> u.getUserId().equals(newUsername));

        if(hasSameUserName){
            return Optional.empty();
        }

        User user1 = userRepository.findById(userId).get();

        user1.setUsername(newUsername);
        user1.setPassword(new BCryptPasswordEncoder().encode(password));

        return Optional.of(userRepository.save(user1));
    }

    @Override
    public void deleteById(String userId) {

        transactionService.deleteAllTransactionsOfUser(userId);
        portfolioService.deleteAllPortfoliosOfUser(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<String> login(final String username, final String password) {

        User user = userRepository.findByUsername(username);

        if (user != null) {

            boolean passwordEqual = new BCryptPasswordEncoder().matches(password,user.getPassword());

            if(passwordEqual){
                final String uuid = UUID.randomUUID().toString();
                user.setToken(uuid);
                userRepository.save(user);
                return Optional.ofNullable(user.getToken());
            }
            return Optional.of("Wrong password: " + username);
        } else {
            return Optional.of("User not found with username: " + username);
            }
    }

    @Override
    public void logout(User user) {
        user.setToken("");
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByToken(String token) {
        Optional<User> tempUser = userRepository.findByToken(token);

        if(tempUser.isPresent()){
            User user1 = tempUser.get();

            return Optional.of(user1);
        }

        return Optional.empty();
    }

    @Override
    public User findById(String id) {
        Optional<User> user= userRepository.findById(id);
        return user.orElse(null);
    }
}


