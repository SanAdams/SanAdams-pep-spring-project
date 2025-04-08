package com.example.exception.account;

public class AccountAlreadyExistsException extends AccountCreationException {
    public AccountAlreadyExistsException(String message){
        super(message);
    }
}
