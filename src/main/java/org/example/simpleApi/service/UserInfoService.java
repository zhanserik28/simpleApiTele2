package org.example.simpleApi.service;

import org.example.simpleApi.dao.UserInfoRepository;
import org.example.simpleApi.dao.UserRepository;
import org.example.simpleApi.model.UserInfo;
import org.example.simpleApi.model.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository userRepository;

    public List<UserInfo> findAll() {
        return userRepository.findAll();
    }

    public UserInfo findOne(long id) {
        Optional<UserInfo> foundPerson = userRepository.findById(id);
        return foundPerson.orElse(null);
    }


    @Transactional
    public void save(UserInfo person) {
        userRepository.save(person);
    }

    @Transactional
    public void update(Long id, UserInfo updatedPerson) {
        updatedPerson.setId(id);
        userRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
