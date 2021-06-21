package org.example.simpleApi.service;

import org.example.simpleApi.dao.UserRepository;
import org.example.simpleApi.model.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserTable> findAll() {
        return userRepository.findAll();
    }

    public UserTable findOne(int id) {
        Optional<UserTable> foundPerson = userRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public List<UserTable> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void save(UserTable person) {
        userRepository.save(person);
    }

    @Transactional
    public void update(int id, UserTable updatedPerson) {
        updatedPerson.setId(id);
        userRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public void deleteById(long id) {
        userRepository.deleteById((int)id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
