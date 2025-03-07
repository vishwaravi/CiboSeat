package com.vishwaravi.ciboseat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vishwaravi.ciboseat.models.BillModel;

public interface BillRepo extends JpaRepository<BillModel,Long> {
    
    @Query(value = "SELECT * FROM bill_table WHERE customer_group_id = :groupId",nativeQuery = true)    
    BillModel findByGroupId(@Param("groupId") long groupId);
}
