package com.cropwise.cw_system.exception;

public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException(String username) {
        super("Username " + username + " not found");
    }
}
