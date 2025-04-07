package com.example.exception;

public class BlankUsernameException extends AccountCreationException{
    public BlankUsernameException(String message){
        super(message);
    }
}
