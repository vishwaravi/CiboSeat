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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dining_table")
public class DiningTableModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "waitstaff_id",referencedColumnName = "id")
    private WaitstaffModel waitStaffId;

    @Column(name="seat_count")
    private Integer seatCount;

    @Column(name="time_stamp",nullable = false)
    @CreationTimestamp
    private LocalDateTime timeStamp;

    @Column(name="last_modified",nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastModified;
}
