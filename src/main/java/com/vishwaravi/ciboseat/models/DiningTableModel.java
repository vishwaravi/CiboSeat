package com.vishwaravi.ciboseat.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_orders")
public class DiningTableModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "waitstaff_id",nullable = true)
    private Long waitStaffId;

    @Column(name="seat_count")
    private Integer SeatCount;

    @Column(name = "occupied_count")
    private Integer occupiedCount;

    @Column(name="time_stamp",nullable = false)
    @CreationTimestamp
    private LocalDateTime timeStamp;
}
