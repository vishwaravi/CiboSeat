package com.vishwaravi.ciboseat.exceptions;
/*
 * Custom Exception for WaitStaffNotFound Error. that extends Runtime Exception.
 */
public class WaitStaffNotFoundException extends RuntimeException{


    /**
     * Constructor for the WaitStaffNotFound Exception.
     * @param msg - error message.
     */
    public WaitStaffNotFoundException(String msg){
        super(msg);
    }
}
