package com.vishwaravi.ciboseat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillFood {
    private String name;
    private Integer price;
    private Integer quantity;
}
