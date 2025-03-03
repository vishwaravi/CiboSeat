package com.vishwaravi.ciboseat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vishwaravi.ciboseat.models.CustomerGroupsModel;

import java.util.List;

/**
 * Custom Repository for Customer Groups Table CRUD oprations.
 */
public interface CustomerGroupsRepo extends JpaRepository<CustomerGroupsModel,Long>{
    /**
     * Function for Retriving Customer Groups by table Id.
     * @param tableId
     * @return List of Customer Groups Model Details.
     */
    List<CustomerGroupsModel> findByDiningTableId(long tableId);

    @Query(value = "SELECT SUM(customer_count) FROM customer_groups WHERE table_id = :tableId", nativeQuery = true)
    Integer getOccupiedSeatCountByTableId(@Param("tableId") long tableId);

}
