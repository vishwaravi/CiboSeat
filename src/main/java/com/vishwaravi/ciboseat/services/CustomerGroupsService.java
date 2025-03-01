package com.vishwaravi.ciboseat.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vishwaravi.ciboseat.dto.CustomerGroupsDto;
import com.vishwaravi.ciboseat.exceptions.DataNotFoundException;
import com.vishwaravi.ciboseat.exceptions.DiningTableFullException;
import com.vishwaravi.ciboseat.exceptions.InvalidInputException;
import com.vishwaravi.ciboseat.models.CustomerGroupsModel;
import com.vishwaravi.ciboseat.models.DiningTableModel;
import com.vishwaravi.ciboseat.repositories.CustomerGroupsRepo;
import com.vishwaravi.ciboseat.repositories.DiningTableRepo;

/**
 * Service class Customer groups to manage Customer Groups Oprations.
 */
@Service
public class CustomerGroupsService {
    private CustomerGroupsRepo customerGroupsRepo;
    private DiningTableRepo diningTableRepo;

    /**
     * Denpencies needed for this CustomerGroupsService. 
     * @param customerGroupsRepo
     * @param diningTableRepo
     */
    public CustomerGroupsService(
        CustomerGroupsRepo customerGroupsRepo,
        DiningTableRepo diningTableRepo 
        ) {
        this.customerGroupsRepo = customerGroupsRepo;
        this.diningTableRepo = diningTableRepo;
    }

    /**
     * Function for Retriving the Customer Groups by the Table Id.
     * @param tableId - table id of type Long. 
     * @return returns MAP< customerGroupId, List of Seats Filled >.
     */
    public Map<Long,List<String>> getGroupsByTable(long tableId){
        List<CustomerGroupsModel> customerGroups = customerGroupsRepo.findByDiningTableId(tableId);
        //Map<GroupId,List of Filled Seats>
        Map<Long,List<String>> groupsWithFilledSeats = new HashMap<>();
        
        for (CustomerGroupsModel group:customerGroups){
            groupsWithFilledSeats.put(group.getId(),group.getSeats());
        }
        return groupsWithFilledSeats;
    }

    /**
     * Function for Create a Customer Group for manage Orders efficiently.
     * @param customerGroupsDto - customer group details.
     * @return created Customer Groups Model
     */
    @Transactional
    public CustomerGroupsModel createGroup(CustomerGroupsDto customerGroupsDto){
        long tableId = customerGroupsDto.getTableId();
        Optional<DiningTableModel> tableOpnl = diningTableRepo.findById(tableId);

        if(!tableOpnl.isPresent())
            throw new DataNotFoundException("Table with Id "+tableId+" Not found");
        else{
            if(customerGroupsDto.getSeats().size() == customerGroupsDto.getSeatCount()){
                int occupiedCount = tableOpnl.get().getOccupiedCount()==null?0:tableOpnl.get().getOccupiedCount();
                if(tableOpnl.get().getOccupiedCount() == tableOpnl.get().getSeatCount())
                    throw new DiningTableFullException("Dining Table is Full ");
                    
                tableOpnl.get().setOccupiedCount(occupiedCount+customerGroupsDto.getSeats().size());
                diningTableRepo.save(tableOpnl.get());
                CustomerGroupsModel customerGroupsModel = CustomerGroupsModel.builder()
                    .customerCount(customerGroupsDto.getSeatCount())
                    .diningTable(tableOpnl.get())
                    .seats(customerGroupsDto.getSeats())
                    .build();
                return customerGroupsRepo.save(customerGroupsModel);
            }
            else
                throw new InvalidInputException("The CustomerCount and Seat Allocation Doesn't match");
        }
    }

    public boolean deleteCustomerGroupById(long id){
        if(customerGroupsRepo.existsById(id)){
            customerGroupsRepo.deleteById(id);
        return true;
        }
        else throw new DataNotFoundException("customer Group Not Found With ID :"+id);
    }
}
