package com.example.exception;

public class AccountAlreadyExistsException extends AccountCreationException {
    public AccountAlreadyExistsException(String message){
        super(message);
    }
}
