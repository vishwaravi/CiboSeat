package com.vishwaravi.ciboseat.exceptions;

public class DiningTableFullException extends RuntimeException{
 
    public DiningTableFullException(String message){
        super(message);
    }
}
