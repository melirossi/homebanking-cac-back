package com.ar.bankingmelisa.domain.exceptions;

// This class represents an exception that is thrown when a user is not found.
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        // The constructor takes a message as a parameter and passes it to the superclass constructor.
        super(message);
    }
}

