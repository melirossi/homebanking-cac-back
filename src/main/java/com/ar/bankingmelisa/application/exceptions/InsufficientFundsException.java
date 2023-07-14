package com.ar.bankingmelisa.application.exceptions;

public class InsufficientFundsException extends RuntimeException{

    /*
    Constructs a new InsufficientFundsException with the specified detail message.
    @param message the detail message.
    */
    public InsufficientFundsException(String message) {
        super(message);
    }
}
