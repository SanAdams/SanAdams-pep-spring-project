package com.example.exception.login;

public class PasswordDoesNotMatchException extends LoginException{
    public PasswordDoesNotMatchException(String message){
        super(message);
    }
}
