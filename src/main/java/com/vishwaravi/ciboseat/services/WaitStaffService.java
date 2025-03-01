package com.vishwaravi.ciboseat.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vishwaravi.ciboseat.dto.AssignedStaffInfoRes;
import com.vishwaravi.ciboseat.exceptions.DataNotFoundException;
import com.vishwaravi.ciboseat.exceptions.WaitStaffNotAssignableException;
import com.vishwaravi.ciboseat.models.DiningTableModel;
import com.vishwaravi.ciboseat.models.WaitstaffModel;
import com.vishwaravi.ciboseat.repositories.DiningTableRepo;
import com.vishwaravi.ciboseat.repositories.WaistaffRepo;
/*
 * Service class for Wait Staff Operations
 */
@Service
public class WaitStaffService {
    private WaistaffRepo waistaffRepo;
    private DiningTableRepo diningTableRepo;

    public WaitStaffService(WaistaffRepo waistaffRepo,DiningTableRepo diningTableRepo){
        this.waistaffRepo = waistaffRepo;
        this.diningTableRepo = diningTableRepo; 
    }

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
        if(staff.isPresent())
            return staff.get();
        else
            throw new DataNotFoundException("WaitStaff with name "+name+" NotFound");
    }

    /**
     * Function for assigning table to specific table by id.
     * @param staffId
     * @param tableId
     * @return returns a AssignedStaffInfoRes object.
     */
    public AssignedStaffInfoRes assignStaffToTable(long staffId,long tableId){
        Optional<DiningTableModel> table = diningTableRepo.findById(tableId);
        if(table.isPresent()){
            Optional<WaitstaffModel> staff = waistaffRepo.findById(staffId);
            if(staff.isPresent()){
                if (staff.get().isWaitStaffAssigned()) 
                    throw new WaitStaffNotAssignableException("waitStaff with id "+staffId+" already assigned to other table");

                table.get().setWaitStaffId(staff.get());
                staff.get().setWaitStaffAssigned(true);
                diningTableRepo.save(table.get());
                waistaffRepo.save(staff.get());
                return AssignedStaffInfoRes.builder()
                    .tableId(tableId)
                    .waitStaffId(staffId)
                    .timeStamp(staff.get().getTimeStamp())
                    .build();
            }
            else throw new DataNotFoundException("WaitStaff with ID "+staffId+" NotFound");
        }
        else throw new DataNotFoundException("Table with ID "+tableId+" not Found");
    }
}
