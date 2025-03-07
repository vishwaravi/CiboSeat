package com.vishwaravi.ciboseat.models;

import jakarta.persistence.Id;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_orders")
public class OrdersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name="group_id",nullable = false)
    private long groupId;

    @Column(name="dish_id",nullable = false)
    private long dishId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Transient
    private Boolean servedStatus = false;

    @Column(name="total_amount")
    private Integer totalAmount;

    @Column(name="time_stamp",nullable = false)
    @CreationTimestamp
    private LocalDateTime timeStamp;

    @Column(name="last_modified",nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastModified;
}
