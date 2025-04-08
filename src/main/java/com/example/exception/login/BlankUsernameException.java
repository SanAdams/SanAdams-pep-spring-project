package com.example.exception.login;

public class BlankUsernameException extends LoginException{
    public BlankUsernameException(String message){
        super(message);
    }
}
