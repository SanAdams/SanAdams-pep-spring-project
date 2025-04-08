package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.account.AccountDoesNotExistException;
import com.example.exception.message.MessageBlankException;
import com.example.exception.message.MessageLengthException;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message newMessage){
        meetsConstraints(newMessage);
        return messageRepository.save(newMessage);
    }

    public boolean meetsConstraints(Message message){
        if(message.getMessageText().isBlank()){
            throw new MessageBlankException("Message cannot be blank");    
        }
        else if (message.getMessageText().length() > 255){
            throw new MessageLengthException("Message cannot be over 255 characters");
        } 
        else if (!messageRepository.accountExists(message.getPostedBy())){
            throw new AccountDoesNotExistException("The account " + message.getPostedBy() + " does not exist");
        }        
        return true;
    }
}
