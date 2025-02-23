package com.vishwaravi.ciboseat.exceptions;

/*
 * Custom Exception for WaitStaffNotAssignable Error. that extends Runtime Exception.
 */
public class WaitStaffNotAssignableException extends RuntimeException{
    
    /**
     * Constructor for the WaitStaffNotAssignable Exception.
     * @param message - error message.
     */
    public WaitStaffNotAssignableException(String message){
        super(message);
    }
}
