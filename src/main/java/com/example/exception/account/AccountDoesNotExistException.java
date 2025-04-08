package com.example.exception.account;

import com.example.exception.login.LoginException;

public class AccountDoesNotExistException extends LoginException{
    public AccountDoesNotExistException(String message){
        super(message);
    }
}
