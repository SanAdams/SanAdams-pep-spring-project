package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.exception.account.AccountAlreadyExistsException;
import com.example.exception.account.AccountDoesNotExistException;
import com.example.exception.login.BlankUsernameException;
import com.example.exception.login.PasswordDoesNotMatchException;
import com.example.exception.login.PasswordLengthException;
import com.example.exception.message.MessageBlankException;
import com.example.exception.message.MessageLengthException;
import com.example.exception.message.MessageNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    /* ACCOUNT EXCEPTIONS */
    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<String> handleAccountAlreadyExists(AccountAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Account Already Exists: " + e.getMessage());
    }

    @ExceptionHandler(AccountDoesNotExistException.class)
    public ResponseEntity<String> handleAccountDoesNotExist(AccountDoesNotExistException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Input: " + e.getMessage());
    }

    /* LOGIN EXCEPTIONS */
    @ExceptionHandler(BlankUsernameException.class)
    public ResponseEntity<String> handleBlankUsername(BlankUsernameException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Input: " + e.getMessage());
    }

    @ExceptionHandler(PasswordDoesNotMatchException.class)
    public ResponseEntity<String> handlePasswordDoesNotMatch(PasswordDoesNotMatchException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Failed: " + e.getMessage());
    }

    @ExceptionHandler(PasswordLengthException.class)
    public ResponseEntity<String> handlePasswordLength(PasswordLengthException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Input: " + e.getMessage());
    }

    /* MESSAGE EXCEPTIONS */
    @ExceptionHandler({MessageBlankException.class, MessageLengthException.class})
    public ResponseEntity<String> handleMessageValidation(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Message: " + e.getMessage());
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<String> handleMessageNotFound(MessageNotFoundException e) {
        return ResponseEntity.ok().body(e.getMessage());
    }
}