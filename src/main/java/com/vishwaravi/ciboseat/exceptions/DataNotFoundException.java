package com.vishwaravi.ciboseat.exceptions;
/**
 * Common Exception for Data Entry Not found Errors
 */
public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String message){
        super(message);
    }
}