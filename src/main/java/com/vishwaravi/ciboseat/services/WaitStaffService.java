package com.vishwaravi.ciboseat.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishwaravi.ciboseat.exceptions.WaitStaffNotFoundException;
import com.vishwaravi.ciboseat.models.WaitstaffModel;
import com.vishwaravi.ciboseat.repositories.WaistaffRepo;
/*
 * Service class for Wait Staff Operations
 */
@Service
public class WaitStaffService {
    
    @Autowired
    WaistaffRepo waistaffRepo;

    /**
     * Function for saving WaitStaff Details to DB using repo.
     * @param waitstaff - waitstaff model.
     * @return - saved WaitStaff Details.
     */
    public WaitstaffModel addWaitStaff(WaitstaffModel waitstaff){
        return waistaffRepo.save(waitstaff);
    }

    /**
     * Function for Retriving Details of Wait Staff from DB.
     * @param name - wait staff name.
     * @return - WaitStaff details.
     */
    public WaitstaffModel getWaiterDetailsByName(String name){
        Optional<WaitstaffModel> staff =  waistaffRepo.findByName(name);
        if(staff.isPresent()){
            return staff.get();
        }
        else throw new WaitStaffNotFoundException("WaitStaff with name "+name+" NotFound");
    }

}
