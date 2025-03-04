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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishwaravi.ciboseat.exceptions.DataNotFoundException;
import com.vishwaravi.ciboseat.models.DishModel;
import com.vishwaravi.ciboseat.repositories.DishRepo;
import com.vishwaravi.ciboseat.services.DishService;

@RestController()
@RequestMapping("/dishes")
public class DishController {
    
    private DishService dishService;
    private DishRepo dishRepo;

    private DishController(DishService dishService,DishRepo dishRepo) {
        this.dishService = dishService;
        this.dishRepo = dishRepo;
    }

    @GetMapping
    private ResponseEntity<List<DishModel>> getDishes(){
        List<DishModel> dishes = dishRepo.findAll();
        return new ResponseEntity<>(dishes,HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<DishModel> addDish(@RequestBody DishModel dish){
        return new ResponseEntity<DishModel>(dishService.addDish(dish),HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{dishId}")
    public ResponseEntity<Map<String,String>> removeDish(@PathVariable long dishId){
        if(!dishRepo.existsById(dishId)) throw new DataNotFoundException("Dish With Id "+dishId+" Not Found");
        else {
            dishRepo.deleteById(dishId);
            Map<String,String> res = new HashMap<>();
            res.put("status","deleted");
            return new ResponseEntity<>(res,HttpStatus.OK);
        }
    }

    @PutMapping("/update/{dishId}")
    public ResponseEntity<DishModel> updateDish(@PathVariable long dishId, @RequestBody DishModel dishModel){
        DishModel updatedDish = dishService.updateDish(dishId, dishModel);
        return new ResponseEntity<>(updatedDish,HttpStatus.OK);
    }
}
