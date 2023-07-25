package com.wirail.DBTest;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    // Custom query methods can be added here if needed
    Optional<User> getUserByEmail(String email);

}
