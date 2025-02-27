package com.vishwaravi.ciboseat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishwaravi.ciboseat.models.CustomerGroupsModel;

import java.util.List;


public interface CustomerGroupsRepo extends JpaRepository<CustomerGroupsModel,Long>{
    List<CustomerGroupsModel> findByDiningTableId(long tableId);
}
