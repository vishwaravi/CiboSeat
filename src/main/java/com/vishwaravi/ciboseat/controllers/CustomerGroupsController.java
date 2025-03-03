package com.vishwaravi.ciboseat.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<List<CustomerGroupsModel>> getFilledSeats(@PathVariable long tableId){
        List<CustomerGroupsModel> list = customerGroupsService.getGroupsByTable(tableId);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @DeleteMapping("/delete_group/{groupId}")
    public ResponseEntity<Map<String,String>> deleteCustomerGroup(@PathVariable long groupId){
        if(customerGroupsService.deleteCustomerGroupById(groupId)){
            Map<String,String> res = new HashMap<>();
            res.put("status","deleted");
            return new ResponseEntity<>(res,HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
    }
}
