package com.example.exception.login;

import com.example.exception.account.AccountCreationException;

public class BlankUsernameException extends AccountCreationException{
    public BlankUsernameException(String message){
        super(message);
    }
}
