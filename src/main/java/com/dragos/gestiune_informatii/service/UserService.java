package com.dragos.gestiune_informatii.service;

import com.dragos.gestiune_informatii.model.User;
import com.dragos.gestiune_informatii.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register a new user
    public void registerNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        user.setRole("ROLE_USER"); // Default role
        userRepository.save(user);
    }

    // Load user by username (for authentication)
    public Optional<User> loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
