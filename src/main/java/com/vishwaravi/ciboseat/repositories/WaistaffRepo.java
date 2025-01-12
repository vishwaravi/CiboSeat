package com.vishwaravi.ciboseat.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vishwaravi.ciboseat.models.WaitstaffModel;

@Repository
public interface WaistaffRepo extends JpaRepository<WaitstaffModel,Long>{
    Optional<WaitstaffModel> findByName(String username);
}
