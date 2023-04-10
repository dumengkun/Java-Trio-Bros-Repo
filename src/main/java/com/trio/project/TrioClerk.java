package com.trio.project;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TrioClerk {
    
    private final UserRepository userRepository;

    public void setStudent(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

}