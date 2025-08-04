package com.main.app.Services;

import com.main.app.Model.User;
import com.main.app.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        userRepository.save(user);
    }



    public User findByUsername(String username) {

        return userRepository.findByUsername(username);

    }

   public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    public void deleteById(Long userId) {
        userRepository.deleteById(userId);

    }
}