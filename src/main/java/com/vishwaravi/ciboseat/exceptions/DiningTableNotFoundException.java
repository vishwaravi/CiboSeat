package com.vishwaravi.ciboseat.exceptions;
/**
 * Custom Exception for DiningTableNotFound Error. that extends Runtime Exception.
 */
public class DiningTableNotFoundException extends RuntimeException{
    /**
     * Constructor for the DiningTableNotFound Exception.
     * @param message - error message.
     */
    public DiningTableNotFoundException(String message){
        super(message);
    }
}
