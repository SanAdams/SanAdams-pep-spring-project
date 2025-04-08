package com.example.exception;

public class AccountDoesNotExistException extends LoginException{
    public AccountDoesNotExistException(String message){
        super(message);
    }
}
