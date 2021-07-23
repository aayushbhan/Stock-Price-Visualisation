package com.Visualizer.Stockopedia.Repository;

import com.Visualizer.Stockopedia.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User,String>{

    User findByUsername(String username);
    Optional<User> findByToken(String token);

}

