package com.vishwaravi.ciboseat.controllers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vishwaravi.ciboseat.dto.CustomerGroupsDto;
import com.vishwaravi.ciboseat.models.CustomerGroupsModel;
import com.vishwaravi.ciboseat.services.CustomerGroupsService;

@RestController
public class CustomerGroupsController {
    private CustomerGroupsService customerGroupsService;
    public CustomerGroupsController(CustomerGroupsService customerGroupsService){
        this.customerGroupsService = customerGroupsService;
    }

    @PutMapping("/creategroup")
    public CustomerGroupsModel createTableGroup(@RequestBody CustomerGroupsDto customerGroupsDto){
        return customerGroupsService.createGroup(customerGroupsDto);
    }

    @GetMapping("/get_filled_seats/{tableId}")
    public ResponseEntity<?> getFilledSeats(@PathVariable long tableId){
        List<String> list = customerGroupsService.getFilledSeats(tableId);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
