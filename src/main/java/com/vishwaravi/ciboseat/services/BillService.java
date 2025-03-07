package com.vishwaravi.ciboseat.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vishwaravi.ciboseat.dto.BillFood;
import com.vishwaravi.ciboseat.exceptions.DataNotFoundException;
import com.vishwaravi.ciboseat.models.BillModel;
import com.vishwaravi.ciboseat.models.CustomerGroupsModel;
import com.vishwaravi.ciboseat.models.DishModel;
import com.vishwaravi.ciboseat.models.OrdersModel;
import com.vishwaravi.ciboseat.repositories.BillRepo;
import com.vishwaravi.ciboseat.repositories.CustomerGroupsRepo;
import com.vishwaravi.ciboseat.repositories.DishRepo;
import com.vishwaravi.ciboseat.repositories.OrdersRepo;

@Service
public class BillService {
    
    private CustomerGroupsRepo customerGroupsRepo;
    private DishRepo dishRepo;
    private OrdersRepo ordersRepo;
    private BillRepo billRepo;
    private BillService(CustomerGroupsRepo customerGroupsRepo,
        DishRepo dishRepo,OrdersRepo ordersRepo,BillRepo billRepo){
        
        this.billRepo = billRepo;
        this.dishRepo = dishRepo;
        this.ordersRepo = ordersRepo;
        this.customerGroupsRepo = customerGroupsRepo;
    }

    public BillModel generateBill(long groupId){
        BillModel existingBill = billRepo.findByGroupId(groupId);
        
        if(existingBill!=null) 
            return existingBill;

        if(!customerGroupsRepo.existsById(groupId))
            throw new DataNotFoundException("Customer Group with ID: "+groupId+" Not Found");
        else {
            CustomerGroupsModel group = customerGroupsRepo.findById(groupId).get();
            List<OrdersModel> orders = ordersRepo.findByGroupId(groupId);
            List<BillFood> foods = new ArrayList<>();
            long subTotal = 0;
            int gst = 5;
            for(OrdersModel order:orders){
                DishModel dish = dishRepo.findById(order.getDishId()).get();
                foods.add(new BillFood(dish.getName(),dish.getPrice(),order.getQuantity()));
                subTotal += dish.getPrice() * order.getQuantity();
            }
            int gstAmount = (int)(gst*subTotal)/100;

            BillModel bill = BillModel.builder()
                .customerGroup(group)
                .diningTable(group.getDiningTable())
                .dishes(foods)
                .gst(gst)
                .subTotal(subTotal)
                .totalAmount(subTotal+gstAmount)
                .build();
            return billRepo.save(bill);
        }
    }
}
