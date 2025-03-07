package com.vishwaravi.ciboseat.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishwaravi.ciboseat.models.WaitstaffModel;

/*
 * Custom Repository for Wait Staff Table CRUD oprations.
 */
public interface WaistaffRepo extends JpaRepository<WaitstaffModel,Long>{
    /**
     * JPA Function for Retriving Wait Staff Details.
     * @param username - waitStaff name.
     * @return - Wait Staff Deatils.
     */
    Optional<WaitstaffModel> findByName(String username);
}
