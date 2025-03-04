package com.vishwaravi.ciboseat.services;

import org.springframework.stereotype.Service;

import com.vishwaravi.ciboseat.models.OrdersModel;
import com.vishwaravi.ciboseat.repositories.DishRepo;
import com.vishwaravi.ciboseat.repositories.OrdersRepo;

@Service
public class OrderService {
    private OrdersRepo ordersRepo;
    private DishRepo dishRepo;

    private OrderService(OrdersRepo ordersRepo,DishRepo dishRepo){
        this.ordersRepo = ordersRepo;
        this.dishRepo = dishRepo;
    }

    public OrdersModel createFoodOrder(OrdersModel orderDetails){
        int priceOfDish = dishRepo.findPriceByDishId(orderDetails.getDishId());
        int totalAmount = priceOfDish * orderDetails.getQuantity();
        orderDetails.setTotalAmount(totalAmount);
        return ordersRepo.save(orderDetails);
    }
}
