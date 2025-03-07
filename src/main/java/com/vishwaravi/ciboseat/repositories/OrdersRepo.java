package com.vishwaravi.ciboseat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishwaravi.ciboseat.models.OrdersModel;
import java.util.List;


public interface OrdersRepo extends JpaRepository<OrdersModel,Long>{
    List<OrdersModel> findByGroupId(long groupId);
}
