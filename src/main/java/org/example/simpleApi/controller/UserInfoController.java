package org.example.simpleApi.controller;

import org.example.simpleApi.model.UserInfo;
import org.example.simpleApi.model.UserTable;
import org.example.simpleApi.service.UserInfoService;
import org.example.simpleApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserInfoController {
    private final UserInfoService userService;

    public UserInfoController(UserInfoService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        List<UserInfo> userTables =  userService.findAll();

        if(userTables.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userTables, HttpStatus.OK);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<UserInfo> getUserById(@PathVariable("id") long id) {
        UserInfo userData = userService.findOne( id);
        if (userData != null) {
            return new ResponseEntity<>(userData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/info")
    public ResponseEntity<UserInfo> createTutorial(@RequestBody UserInfo userTable) {
        try {
            userService.save(userTable);
            return new ResponseEntity<>( HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/info/{id}")
    public ResponseEntity<UserInfo> updateTutorial(@PathVariable("id") long id, @RequestBody UserInfo userTable) {
        UserInfo userData = userService.findOne( id);

        if (userData != null) {
            userData.setFirstName(userTable.getFirstName());
            userData.setLastName(userTable.getLastName());
            userData.setAge(userTable.getAge());
            userData.setNumber(userTable.getNumber());
            userService.save(userData);
            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/info/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/info")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            userService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
