package com.vishwaravi.ciboseat.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vishwaravi.ciboseat.exceptions.WaitStaffNotFoundException;
/**
 * Global Exception Handler for Handling Runtime Exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Function for Handling WaitStaffNotFound Exception
     * @param e - WaitStaffNotFoundException 
     * @return - Custom response with error code and error message to the client.
     */
    @ExceptionHandler(WaitStaffNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String,String>> WaitStaffNotFoundException(WaitStaffNotFoundException e){
        Map<String,String> res = new HashMap<>();
        res.put("error","404 - Not Found");
        res.put("message", e.getMessage());
        return new ResponseEntity<Map<String,String>>(res,HttpStatus.NOT_FOUND);
    }
}
