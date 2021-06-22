package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Integer id){
        super("Could not find user id: " + id);
    }

    public UserNotFoundException(String username){
        super("Could not find user username: " + username);
    }
}
