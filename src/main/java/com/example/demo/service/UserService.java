package com.example.demo.service;

import com.example.demo.model.Contact;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public User findById(Integer id){
        Optional<User> foundContact = userRepository.findById(id);
        return foundContact.orElse(null);
    }

    public void saveOrUpdate(User contact){
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

}
