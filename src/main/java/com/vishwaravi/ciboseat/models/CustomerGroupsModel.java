package com.vishwaravi.ciboseat.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_groups")
public class CustomerGroupsModel {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "table_id")
    private DiningTableModel diningTable;

    @Column(name = "customer_count")
    private Integer customerCount;

    @Column(name="time_stamp",nullable = false)
    @CreationTimestamp
    private LocalDateTime timeStamp;

    @Column(name="last_modified",nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastModified;
}
