package com.example.exception;

public class PasswordLengthException extends AccountCreationException{
    public PasswordLengthException(String message){
        super(message);
    }
}
