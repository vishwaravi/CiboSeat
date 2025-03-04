package com.vishwaravi.ciboseat.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishwaravi.ciboseat.exceptions.DataNotFoundException;
import com.vishwaravi.ciboseat.models.OrdersModel;
import com.vishwaravi.ciboseat.repositories.OrdersRepo;
import com.vishwaravi.ciboseat.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private OrdersRepo ordersRepo;
    public OrderController(OrderService orderService,OrdersRepo ordersRepo){
        this.orderService = orderService;
        this.ordersRepo = ordersRepo;
    }

    @GetMapping
    public ResponseEntity<List<OrdersModel>> getAllOrders(){
        List<OrdersModel> orders = ordersRepo.findAll();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrdersModel> getAllOrders(@PathVariable long orderId){
        if(!ordersRepo.existsById(orderId))
            throw new DataNotFoundException("Order information with ID :"+orderId+" Not Found");
        else {
            OrdersModel order = ordersRepo.findById(orderId).get();
            return new ResponseEntity<>(order,HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<OrdersModel> createFoodOrder(@RequestBody OrdersModel orderDetails){
        OrdersModel response = orderService.createFoodOrder(orderDetails);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<Map<String,String>> cancelOrder(@PathVariable long orderId){
        Map<String,String> response = new HashMap<>();
        if(!ordersRepo.existsById(orderId))
            throw new DataNotFoundException("Order information with ID :"+orderId+" Not Found");
        else{
            ordersRepo.deleteById(orderId);
            response.put("status","order canceled");
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
