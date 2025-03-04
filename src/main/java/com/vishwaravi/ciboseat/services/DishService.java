package com.vishwaravi.ciboseat.services;

import org.springframework.stereotype.Service;

import com.vishwaravi.ciboseat.exceptions.DataNotFoundException;
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

    public DishModel updateDish(long dishId,DishModel dishModel){
        if(!dishRepo.existsById(dishId))
            throw new DataNotFoundException("Dish with ID: "+dishId+" Not Found.");
        else{
            DishModel existingDish = dishRepo.findById(dishId).get();
            
            if(dishModel.getName()!=null)
                existingDish.setName(dishModel.getName());
            if(dishModel.getPrice()!=null)
                existingDish.setPrice(dishModel.getPrice());
            if(dishModel.getGst()!=null)
                existingDish.setGst(dishModel.getGst());
            if(dishModel.getCatagory()!=null)
                existingDish.setCatagory(dishModel.getCatagory());
            
            return dishRepo.save(existingDish);
        }
    }
}
