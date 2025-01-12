package com.vishwaravi.ciboseat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vishwaravi.ciboseat.models.WaitstaffModel;
import com.vishwaravi.ciboseat.services.WaitStaffService;

@RestController
public class WaitstaffController {

    @Autowired
    WaitStaffService waitStaffService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/addstaff")
    ResponseEntity<?> addStaff(@RequestBody WaitstaffModel waitstaff){
        waitstaff.setPassword(passwordEncoder.encode(waitstaff.getPassword()));
        WaitstaffModel addedStaff = waitStaffService.addWaitStaff(waitstaff);
        return new ResponseEntity<>(addedStaff,HttpStatus.CREATED);
    }

    @GetMapping("/home")
    ResponseEntity<?> hello(){
        return new ResponseEntity<>("login successful",HttpStatus.OK);
    }
}
