package com.vishwaravi.ciboseat.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishwaravi.ciboseat.models.BillModel;
import com.vishwaravi.ciboseat.services.BillService;

@RestController
@RequestMapping("/bill")
public class BillController {
    private BillService billService;
    private BillController(BillService billService) {
        this.billService = billService;
    }
    
    @GetMapping("/{groupId}")
    public ResponseEntity<BillModel> generateBill(@PathVariable Long groupId){
        BillModel bill = billService.generateBill(groupId);
        return new ResponseEntity<>(bill,HttpStatus.OK);
    }
}
