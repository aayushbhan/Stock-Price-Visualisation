package com.Visualizer.Stockopedia.Repository;

import com.Visualizer.Stockopedia.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    //find the user - findbyid

    //add new - insert

    //update - save

    //delete - deletebyid
}
