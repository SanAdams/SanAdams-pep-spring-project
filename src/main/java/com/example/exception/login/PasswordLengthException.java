package com.example.exception.login;

public class PasswordLengthException extends LoginException{
    public PasswordLengthException(String message){
        super(message);
    }
}
