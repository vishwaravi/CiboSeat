package com.vishwaravi.ciboseat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vishwaravi.ciboseat.dto.CustomerGroupsDto;
import com.vishwaravi.ciboseat.exceptions.DataNotFoundException;
import com.vishwaravi.ciboseat.exceptions.DiningTableFullException;
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
     * @return returns List of Customer Groups Model.
     */
    public List<CustomerGroupsModel> getGroupsByTable(long tableId){
        if(diningTableRepo.existsById(tableId)){
            List<CustomerGroupsModel> groups = customerGroupsRepo.findByDiningTableId(tableId);
            return groups;
        }
        else throw new DataNotFoundException("Table Not Found With ID :"+tableId);
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
       if(!tableOpnl.isPresent()) throw new DataNotFoundException("Table Not Found With ID :"+tableId);
       else{
            int occpiedCount = customerGroupsRepo.getOccupiedSeatCountByTableId(tableId);
            if(occpiedCount == tableOpnl.get().getSeatCount())
                throw new DiningTableFullException("Dining Table with ID :"+tableId+" is full");

            CustomerGroupsModel group = CustomerGroupsModel.builder()
                .diningTable(tableOpnl.get())
                .customerCount(customerGroupsDto.getSeatCount())
                .build();
            customerGroupsRepo.save(group);
       }
       return null;

    }

    public boolean deleteCustomerGroupById(long id){
        if(customerGroupsRepo.existsById(id)){
            customerGroupsRepo.deleteById(id);
        return true;
        }
        else throw new DataNotFoundException("customer Group Not Found With ID :"+id);
    }
}
