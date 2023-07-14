package com.ar.bankingmelisa.domain.exceptions;

// This class represents an exception that is thrown when an account is not found.
public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException (String message) {
        // The constructor takes a message as a parameter and passes it to the superclass constructor.
        super(message);
    }
}


