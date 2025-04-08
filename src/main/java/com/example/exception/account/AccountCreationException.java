package com.example.exception.account;

public class AccountCreationException extends RuntimeException{
    public AccountCreationException(String message){
        super(message);
    }
}
