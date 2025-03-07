package com.vishwaravi.ciboseat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vishwaravi.ciboseat.models.DishModel;

public interface DishRepo extends JpaRepository<DishModel,Long>{
    
    @Query(value = "SELECT price FROM dish_table WHERE id = :dishId",nativeQuery = true)
    int findPriceByDishId(@Param("dishId") long dishId);

    @Query(value = "SELECT name FROM dish_table WHERE id = :dishId",nativeQuery = true)
    String findDishNameByDishId(@Param("dishId") long dishId);
}
