package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

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
    public ResponseEntity<Account> createAccountHandler(@RequestBody Account newAccount){
        return ResponseEntity.ok(accountService.createAccount(newAccount));
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginHandler(@RequestBody Account loginAccount){
        return ResponseEntity.ok(accountService.login(loginAccount));
    }

    @PostMapping("/messages")
    public ResponseEntity<?> createMessageHandler(@RequestBody Message newMessage){
        return ResponseEntity.ok(messageService.createMessage(newMessage));
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getAllMessagesHandler(){
        return null;
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<?> getByIdHandler(){
        return null;
    }
}
