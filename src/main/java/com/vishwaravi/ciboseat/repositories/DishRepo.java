package com.vishwaravi.ciboseat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishwaravi.ciboseat.models.DishModel;

public interface DishRepo extends JpaRepository<DishModel,Long>{
    
}
