package com.example.exception;

public class PasswordDoesNotMatchException extends LoginException{
    public PasswordDoesNotMatchException(String message){
        super(message);
    }
}
