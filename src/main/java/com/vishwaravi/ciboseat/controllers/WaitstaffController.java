package com.vishwaravi.ciboseat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vishwaravi.ciboseat.models.WaitstaffModel;
import com.vishwaravi.ciboseat.services.WaitStaffService;

/**
 * Controller for handling Waistaff Requests.
 */
@RestController
public class WaitstaffController {

    @Autowired
    WaitStaffService waitStaffService;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    /**
     *  EndPoint Test Function for checking the login Functionality.
     * @return - String with the login Status.
     */
    @GetMapping("/login")
    ResponseEntity<String> hello(){
        return new ResponseEntity<>("login successful",HttpStatus.OK);
    }

    /**
     * Endpoint Function for adding new Wait Staffs.
     * @param waitstaff - WaitStaff Details.
     * @return - created Wait Staff Details.
     */
    @PostMapping("/addstaff")
    ResponseEntity<WaitstaffModel> addStaff(@RequestBody WaitstaffModel waitstaff){
        waitstaff.setPassword(passwordEncoder.encode(waitstaff.getPassword()));
        WaitstaffModel addedStaff = waitStaffService.addWaitStaff(waitstaff);
        return new ResponseEntity<>(addedStaff,HttpStatus.CREATED);
    }

    /**
     * Endpoint Function for retrive the WaitStaff Details
     * @param name
     * @return
     */
    @GetMapping("/waitstaffs/{name}")
    ResponseEntity<WaitstaffModel> findWaitStaffByName(@PathVariable String name){
        WaitstaffModel waitstaff = waitStaffService.getWaiterDetailsByName(name);
        return new ResponseEntity<>(waitstaff,HttpStatus.OK);
    }
}
