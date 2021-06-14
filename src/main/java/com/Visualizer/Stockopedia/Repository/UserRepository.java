package com.Visualizer.Stockopedia.Repository;

import com.Visualizer.Stockopedia.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User,String> {
}
