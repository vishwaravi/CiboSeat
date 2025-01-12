package com.vishwaravi.ciboseat.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishwaravi.ciboseat.models.WaitstaffModel;
import com.vishwaravi.ciboseat.repositories.WaistaffRepo;

@Service
public class WaitStaffService {
    
    @Autowired
    WaistaffRepo waistaffRepo;

    public WaitstaffModel addWaitStaff(WaitstaffModel waitstaff){
        return waistaffRepo.save(waitstaff);
    }

    public WaitstaffModel getWaiterDetailsByName(String name){
        Optional<WaitstaffModel> staff =  waistaffRepo.findByName(name);
        if(staff.isPresent()){
            return staff.get();
        }
        else return null;
    }

}
