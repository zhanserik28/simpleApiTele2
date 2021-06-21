package org.example.simpleApi.controller;

import org.example.simpleApi.model.UserInfo;
import org.example.simpleApi.model.UserTable;
import org.example.simpleApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/persons")
    public ResponseEntity<List<UserTable>> getAllUsers(@RequestParam(required = false, name="username") String username) {
        List<UserTable> userTables;

        if(username == null){
            userTables = userService.findAll();
        } else {
            userTables = userService.findByUsername(username);
        }

        if(userTables.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userTables, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserTable> getUserById(@PathVariable("id") long id) {
        UserTable userData = userService.findOne((int) id);
        if (userData != null) {
            return new ResponseEntity<>(userData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/users")
    public ResponseEntity<UserTable> createTutorial(@RequestBody UserTable userTable) {
        try {
            userService.save(new UserTable(userTable.getUsername(), userTable.getPassword(), userTable.getBirthDate(), null));
            return new ResponseEntity<>( HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserTable> updateTutorial(@PathVariable("id") long id, @RequestBody UserTable userTable) {
        Optional<UserTable> userData = Optional.ofNullable(userService.findOne((int) id));

        if (userData.isPresent()) {
            UserTable userTable1 = userData.get();
            userTable1.setUsername(userTable1.getUsername());
            userTable1.setPassword(userTable1.getPassword());
            userTable1.setUserInfo(userTable1.getUserInfo());
            userService.save(userTable1);
            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            userService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
