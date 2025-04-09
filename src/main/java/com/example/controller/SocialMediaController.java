package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.account.AccountDoesNotExistException;
import com.example.exception.message.MessageNotFoundException;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> loginHandler(@RequestBody Account loginAccount){
        try {
            return ResponseEntity.ok(accountService.login(loginAccount));
        } catch (AccountDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessageHandler(@RequestBody Message newMessage){
        return ResponseEntity.ok(messageService.createMessage(newMessage));
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessagesHandler(){
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageByIdHandler(@PathVariable Integer messageId){
        try {
        return ResponseEntity.ok(messageService.getMessageById(messageId));
        } catch (MessageNotFoundException e) {
            return ResponseEntity.ok().body(null);
        }
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageByIdHandler(@PathVariable Integer messageId){
        int rowsDeleted = messageService.deleteMessageById(messageId);
        if (rowsDeleted == 0) return ResponseEntity.ok(null);
        return ResponseEntity.ok(rowsDeleted); 
    }
    
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageHandler(@RequestBody Map<String, String> requestBody, @PathVariable Integer messageId){
        String messageText = requestBody.get("messageText");
        int rowsUpdated = messageService.updateMessage(messageText, messageId);
        return ResponseEntity.ok(rowsUpdated);
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesFromUserHandler(@PathVariable Integer accountId){
        List<Message> messages = messageService.getAllMessagesByUser(accountId);
        return ResponseEntity.ok(messages);
    }
}
