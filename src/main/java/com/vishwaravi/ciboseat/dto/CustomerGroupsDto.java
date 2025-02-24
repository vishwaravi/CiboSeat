package com.vishwaravi.ciboseat.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomerGroupsDto {
    private long tableId;
    private long waitStaffId;
    private int seatCount;
    private List<String> seats;
}
