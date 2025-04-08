package com.example.exception.message;

public class MessageCreationException extends RuntimeException{
    public MessageCreationException(String message){
        super(message);
    }
}
