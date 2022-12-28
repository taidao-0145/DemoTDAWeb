package com.example.demotda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Revenue {
    private Date date;
    private Long sumQuantity;
    private Long sumTotal;




}
