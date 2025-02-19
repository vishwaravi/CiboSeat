package com.vishwaravi.ciboseat.services;

import org.springframework.stereotype.Service;

import com.vishwaravi.ciboseat.models.DiningTableModel;
import com.vishwaravi.ciboseat.repositories.DiningTableRepo;

/*
 * Service for Dining Table Operations.
 */
@Service
public class DiningTableService {
    
    DiningTableRepo diningTableRepo;

    /**
     * Function for Adding Tables to the DB.
     * @param diningTable - table details.
     * @return - saved Table Details.
     */
    public DiningTableModel addTable(DiningTableModel diningTable){
         return diningTableRepo.save(diningTable);
    }
}
