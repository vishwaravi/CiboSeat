package com.vishwaravi.ciboseat.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignedStaffInfoRes {
    private Long waitStaffId;
    private Long tableId;
    private LocalDateTime timeStamp;
}
