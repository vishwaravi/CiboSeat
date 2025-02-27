package com.vishwaravi.ciboseat.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vishwaravi.ciboseat.dto.CustomerGroupsDto;
import com.vishwaravi.ciboseat.exceptions.DiningTableFullException;
import com.vishwaravi.ciboseat.exceptions.DiningTableNotFoundException;
import com.vishwaravi.ciboseat.exceptions.InvalidInputException;
import com.vishwaravi.ciboseat.models.CustomerGroupsModel;
import com.vishwaravi.ciboseat.models.DiningTableModel;
import com.vishwaravi.ciboseat.repositories.CustomerGroupsRepo;
import com.vishwaravi.ciboseat.repositories.DiningTableRepo;

@Service
public class CustomerGroupsService {
    private CustomerGroupsRepo customerGroupsRepo;
    private DiningTableRepo diningTableRepo;

    public CustomerGroupsService(
        CustomerGroupsRepo customerGroupsRepo,
        DiningTableRepo diningTableRepo 
        ) {
        this.customerGroupsRepo = customerGroupsRepo;
        this.diningTableRepo = diningTableRepo;
    }

    public List<String> getFilledSeats(long tableId){
        List<CustomerGroupsModel> groups = customerGroupsRepo.findByDiningTableId(tableId);
        List<String> filledSeats = new ArrayList<>();
        for(CustomerGroupsModel i:groups){
            for(String j:i.getSeats())
                filledSeats.add(j);
        }
        return filledSeats;
    }

    @Transactional
    public CustomerGroupsModel createGroup(CustomerGroupsDto customerGroupsDto){
        long tableId = customerGroupsDto.getTableId();
        Optional<DiningTableModel> tableOpnl = diningTableRepo.findById(tableId);

        if(!tableOpnl.isPresent())
            throw new DiningTableNotFoundException("Table with Id "+tableId+" Not found");
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
}
