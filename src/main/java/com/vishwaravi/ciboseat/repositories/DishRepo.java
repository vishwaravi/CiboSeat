package com.vishwaravi.ciboseat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vishwaravi.ciboseat.models.DishModel;

public interface DishRepo extends JpaRepository<DishModel,Long>{
    
    @Query(value = "SELECT price from dish_table WHERE id = :dishId",nativeQuery = true)
    int findPriceByDishId(@Param("dishId") long dishId);
}
