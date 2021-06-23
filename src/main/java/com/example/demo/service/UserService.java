package com.example.demo.service;

import com.example.demo.model.Contact;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private HashService hashService;




    public UserService(UserRepository userRepository, HashService hashService) {
        this.userRepository = userRepository;
        this.hashService = hashService;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }


    public User findById(Integer id){
        Optional<User> foundContact = userRepository.findById(id);
        return foundContact.orElse(null);
    }

    public void save(User user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        user.setPassword(hashedPassword);
        user.setSalt(encodedSalt);
        userRepository.save(user);
    }

    public void update(User contact){
        userRepository.save(contact);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public boolean isUsernameAvailable(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isEmpty();
    }
}
