package com.vishwaravi.ciboseat.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name ="dish_table")
public class DishModel {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name="price")
    private Integer price;

    @Column(name="catagory")
    private String catagory;

    @Column(name="gst")
    private Integer gst;

    @Column(name="time_stamp",nullable = false)
    @CreationTimestamp
    private LocalDateTime timeStamp;

    @Column(name="last_modified",nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastModified;
}
