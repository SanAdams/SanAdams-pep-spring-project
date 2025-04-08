package com.example.exception.message;

public class MessageLengthException extends MessageCreationException{
    public MessageLengthException(String message){
        super(message);
    }
}
