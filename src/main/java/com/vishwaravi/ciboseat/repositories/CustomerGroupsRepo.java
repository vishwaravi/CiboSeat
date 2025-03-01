package com.vishwaravi.ciboseat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vishwaravi.ciboseat.models.CustomerGroupsModel;

import java.util.List;

/**
 * Custom Repository for Customer Groups Table CRUD oprations.
 */
@Repository
public interface CustomerGroupsRepo extends JpaRepository<CustomerGroupsModel,Long>{
    /**
     * Function for Retriving Customer Groups by table Id.
     * @param tableId
     * @return List of Customer Groups Model Details.
     */
    List<CustomerGroupsModel> findByDiningTableId(long tableId);

    void deleteById(long id);
}
