package com.example.demotda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopUser {
    private Long idUser;
    private Long sumTotal;
    private Long sumQuantity;

}
