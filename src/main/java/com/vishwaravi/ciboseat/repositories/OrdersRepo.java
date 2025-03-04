package com.vishwaravi.ciboseat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishwaravi.ciboseat.models.OrdersModel;

public interface OrdersRepo extends JpaRepository<OrdersModel,Long>{
    
}
