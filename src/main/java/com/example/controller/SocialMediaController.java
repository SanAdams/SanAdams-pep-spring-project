package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.exception.account.AccountAlreadyExistsException;
import com.example.exception.account.AccountDoesNotExistException;
import com.example.exception.login.BlankUsernameException;
import com.example.exception.login.PasswordDoesNotMatchException;
import com.example.exception.login.PasswordLengthException;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {
    private final MessageService messageService;
    private final AccountService accountService;

    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createAccountHandler(@RequestBody Account newAccount){

        try{
            Account res = accountService.createAccount(newAccount);
            return ResponseEntity.ok()
                   .body(res);
        } catch (AccountAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                   .body(e.getMessage());
        } catch (BlankUsernameException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body(e.getMessage());
        } catch (PasswordLengthException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginHandler(@RequestBody Account loginAccount){
        try {
            Account res = accountService.login(loginAccount);
            return ResponseEntity
                   .ok()
                   .body(res);
        } catch (AccountDoesNotExistException e) {
            return ResponseEntity
                   .status(HttpStatus.UNAUTHORIZED)
                   .body(e.getMessage());
        } catch (PasswordDoesNotMatchException e) {
            return ResponseEntity
                   .status(HttpStatus.UNAUTHORIZED)
                   .body(e.getMessage());
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(){
        return null;
    }
}
