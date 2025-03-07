package com.vishwaravi.ciboseat.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vishwaravi.ciboseat.exceptions.DataNotFoundException;
import com.vishwaravi.ciboseat.exceptions.DiningTableFullException;
import com.vishwaravi.ciboseat.exceptions.InvalidInputException;
import com.vishwaravi.ciboseat.exceptions.WaitStaffNotAssignableException;
/**
 * Global Exception Handler for Handling Runtime Exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Function for Handling DataNotFound Exception
     * @param e - DataNotFoundException 
     * @return - Custom response with error code and error message to the client.
     */
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String,String>> dataNotFoundExceptionHandler(DataNotFoundException e){
        Map<String,String> res = new HashMap<>();
        res.put("error","404 - Not Found");
        res.put("message", e.getMessage());
        return new ResponseEntity<Map<String,String>>(res,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WaitStaffNotAssignableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> waitStaffNotAssignableExceptionHandler(WaitStaffNotAssignableException e){
        Map<String,String> res = new HashMap<>();
        res.put("error","BAD REQUEST");
        res.put("message", e.getMessage());
        return new ResponseEntity<Map<String,String>>(res,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> inavlidInputExceptionHandler(InvalidInputException e){
        Map<String,String> res = new HashMap<>();
        res.put("error","BAD REQUEST");
        res.put("message", e.getMessage());
        return new ResponseEntity<Map<String,String>>(res,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ResponseEntity<Map<String,String>> SQLExceptionHandler(SQLException e){
        Map<String,String> res = new HashMap<>();
        res.put("error","BAD REQUEST");
        res.put("message", "Data Already Persist");
        return new ResponseEntity<Map<String,String>>(res,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DiningTableFullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> DiningTableFullExceptionHandler(DiningTableFullException e){
        Map<String,String> res = new HashMap<>();
        res.put("error","BAD REQUEST");
        res.put("message", e.getMessage());
        return new ResponseEntity<Map<String,String>>(res,HttpStatus.BAD_REQUEST);
    }
}
