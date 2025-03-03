package com.vishwaravi.ciboseat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishwaravi.ciboseat.models.DiningTableModel;

import java.util.Optional;

/*
 * Custom Repository for Dining Table CRUD oprations.
 */
public interface DiningTableRepo extends JpaRepository<DiningTableModel,Long>{
    
    /**
     * JPA Function For Retriving Dining Table Details.
     * @param id - Table id.
     * @return - Returns a Dining table Details.
     */
    Optional<DiningTableModel> findById(Integer id);
}
