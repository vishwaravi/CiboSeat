package com.vishwaravi.ciboseat.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vishwaravi.ciboseat.dto.CustomerGroupsDto;
import com.vishwaravi.ciboseat.exceptions.DiningTableNotFoundException;
import com.vishwaravi.ciboseat.exceptions.InvalidInputException;
import com.vishwaravi.ciboseat.models.CustomerGroupsModel;
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

    public List<String> getAvailableSeats(long tableId){
        List<CustomerGroupsModel> groups = customerGroupsRepo.findByTableId(tableId);
        List<String> availSeats = new ArrayList<>();
        for(CustomerGroupsModel i:groups){
            for(String j:i.getSeats())
                availSeats.add(j);
        }
        return availSeats;
    }

    public CustomerGroupsModel createGroup(CustomerGroupsDto customerGroupsDto){
        long tableId = customerGroupsDto.getTableId();
        if(!diningTableRepo.findById(tableId).isPresent())
            throw new DiningTableNotFoundException("Table with Id "+tableId+" Not found");
        else{
            if(customerGroupsDto.getSeats().size() == customerGroupsDto.getSeatCount()){
                CustomerGroupsModel customerGroupsModel = CustomerGroupsModel.builder()
                    .customerCount(customerGroupsDto.getSeatCount())
                    .tableId(tableId)
                    .seats(customerGroupsDto.getSeats())
                    .build();
                return customerGroupsRepo.save(customerGroupsModel);
            }
            else
                throw new InvalidInputException("The CustomerCount and Seat Allocation Doesn't match");
        }
    }
}
