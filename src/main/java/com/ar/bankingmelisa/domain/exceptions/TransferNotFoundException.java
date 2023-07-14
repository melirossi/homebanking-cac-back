package com.ar.bankingmelisa.domain.exceptions;

// This class represents an exception that is thrown when a transfer is not found.
public class TransferNotFoundException extends RuntimeException{
    public TransferNotFoundException(String message) {
        // The constructor takes a message as a parameter and passes it to the superclass constructor.
        super(message);
    }
}
