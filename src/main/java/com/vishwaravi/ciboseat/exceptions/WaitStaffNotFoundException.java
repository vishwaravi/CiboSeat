package com.vishwaravi.ciboseat.exceptions;
/*
 * Custom Exception for WaitStaffNotFound Error. that extends Runtime Exception.
 */
public class WaitStaffNotFoundException extends RuntimeException{


    /**
     * Constructor for the WaitStaffNotFound Exception.
     * @param message - error message.
     */
    public WaitStaffNotFoundException(String message){
        super(message);
    }
}
