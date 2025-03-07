package com.vishwaravi.ciboseat.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.vishwaravi.ciboseat.dto.BillFood;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bill_table")
public class BillModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="table_id")
    private DiningTableModel diningTable;

    @OneToOne
    @JoinColumn(name = "customer_group_id")
    private CustomerGroupsModel customerGroup;

    @Transient
    private List<BillFood> dishes;
    
    @Column(name = "sub_total")
    private Long subTotal;
    
    @Column(name = "gst")
    private Integer gst;
    
    @Transient
    private Long totalAmount;
    
    @Column(name="time_stamp")
    @CreationTimestamp
    private LocalDateTime timeStamp;

    @Column(name="last_modified",nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastModified;
}
