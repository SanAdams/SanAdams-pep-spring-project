package com.example.exception.login;

import com.example.exception.account.AccountCreationException;

public class PasswordLengthException extends AccountCreationException{
    public PasswordLengthException(String message){
        super(message);
    }
}
