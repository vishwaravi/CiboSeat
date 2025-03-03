package com.vishwaravi.ciboseat.services;

import org.springframework.stereotype.Service;

import com.vishwaravi.ciboseat.models.DishModel;
import com.vishwaravi.ciboseat.repositories.DishRepo;

@Service
public class DishService {
    private DishRepo dishRepo;

    private DishService(DishRepo dishRepo){
        this.dishRepo = dishRepo;
    }

    public DishModel addDish(DishModel dishModel){
        return dishRepo.save(dishModel);
    }
}
