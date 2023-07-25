package com.wirail.DBTest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder= new BCryptPasswordEncoder();
    }

    // Your business logic using the repository methods

    //THIS is useless now that we have implemented the real authentication

    //public User createUser(User user) {
    //    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    //    System.out.println(user.getPassword());
    //    return userRepository.save(user);
    //}

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }


    public Optional<User> findByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public User updateUser(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

}
