package com.vishwaravi.ciboseat.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vishwaravi.ciboseat.dto.AssignedStaffInfoRes;
import com.vishwaravi.ciboseat.models.DiningTableModel;
import com.vishwaravi.ciboseat.repositories.DiningTableRepo;
import com.vishwaravi.ciboseat.services.WaitStaffService;

/** 
 * Controller for handling Dining Table Requests
*/

@RestController
public class DiningTableController {

    private DiningTableRepo diningTableRepo;
    private WaitStaffService waitStaffService;
    DiningTableController(DiningTableRepo diningTableRepo, WaitStaffService waitStaffService){
        this.diningTableRepo = diningTableRepo;
        this.waitStaffService = waitStaffService;
    }
    
    /**
     * Endpoint Function for Adding Tables.
     * @param table - table details.
     * @return - created table object.
     */
    @PostMapping("/addtable")
    public ResponseEntity<DiningTableModel> addTable(@RequestBody DiningTableModel table){
        return new ResponseEntity<>(diningTableRepo.save(table),HttpStatus.CREATED);
    }


    /**
     * Endpoint Function for Retriving all Table Details.
     * @return - List of Table Objects.
     */
    @GetMapping("/tables")
    public ResponseEntity<List<DiningTableModel>> getAllTables(){
        List<DiningTableModel> tables = diningTableRepo.findAll();
        return new ResponseEntity<>(tables,HttpStatus.OK);
    }

    /**
     * Endpoint Function for assigning staff to the Table.
     * @param staffId
     * @param tableId
     * @return - Response with a assigned Information.
     */
    @PutMapping("/assign/{staffId}/table/{tableId}")
    public ResponseEntity<AssignedStaffInfoRes> assignStaffToTable(@PathVariable long staffId,@PathVariable long tableId){
        AssignedStaffInfoRes res = waitStaffService.assignStaffToTable(staffId, tableId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}