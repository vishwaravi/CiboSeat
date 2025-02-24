package com.vishwaravi.ciboseat.dto;

import java.time.LocalDateTime;

import com.vishwaravi.ciboseat.models.WaitstaffModel;

import lombok.Data;

@Data
public class DiningTableDetailsDto {
    private Integer tableId;
    private WaitstaffModel waitStaffId;
    private Integer seatCount;
    private Integer occupiedCount;
    private LocalDateTime timeStamp;
}
