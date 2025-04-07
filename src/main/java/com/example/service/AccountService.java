package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.AccountAlreadyExistsException;
import com.example.exception.BlankUsernameException;
import com.example.exception.PasswordLengthException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account newAccount){
        Account res;
        if (accountRepository.existsByUsername(newAccount.getUsername())){
            throw new AccountAlreadyExistsException("An account with username: "+ newAccount.getUsername() + " already exists");
        } 
        else if(newAccount.getPassword().length() < 4){
            throw new PasswordLengthException("Password must be 4 characters or more");
        }
        else if(newAccount.getUsername().isBlank()){
            throw new BlankUsernameException("Username cannot be blank");
        }
        else{
            accountRepository.save(newAccount);
            res = accountRepository.findByUsername(newAccount.getUsername());
            return res;
        }
    }

    

}
