package com.example.AgileBoard.repository;

import com.example.AgileBoard.model.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by UserDto on 12/10/2017.
 */
@Repository
public interface UserRepository extends MongoRepository<UserDto, Long> {
    UserDto findByUserName(String username);
}
