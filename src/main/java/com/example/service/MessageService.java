package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.account.AccountDoesNotExistException;
import com.example.exception.message.*;
import com.example.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message newMessage){
        meetsPostConstraints(newMessage);
        return messageRepository.save(newMessage);
    }

    public boolean meetsPostConstraints(Message message){
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

    public List<Message> getAllMessages(){
        List<Message> messages = messageRepository.findAll();
        return messages;
    }

    public Message getMessageById(Integer messageId){
        return messageRepository.findById(messageId)
               .orElseThrow(() -> new MessageNotFoundException("No message found with id: " + messageId));
    }

    public int deleteMessageById(Integer messageId){
        return messageRepository.deleteMessageById(messageId);
    }

    public int updateMessage(String newMessageText, int messageId){
        meetsUpdateConstraints(newMessageText, messageId);
        return messageRepository.updateMessage(newMessageText, messageId);
    }

    public boolean meetsUpdateConstraints(String messageText, int messageId){
        if (messageText.isBlank()){
            throw new MessageBlankException("Message cannot be blank");    
        }
        else if (messageText.length() > 255){
            throw new MessageLengthException("Message cannot be over 255 characters");
        } 
        else if (!messageRepository.existsById(messageId)){
            throw new MessageNotFoundException("Message with given ID not found");
        }
        return true;
    }

    public List<Message> getAllMessagesByUser(Integer accountId){
        return messageRepository.findByPostedBy(accountId);
    }
}
