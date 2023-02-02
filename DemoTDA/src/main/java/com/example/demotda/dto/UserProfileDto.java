package com.example.demotda.dto;

import com.example.demotda.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
    private Long id;
    private String fullName;
    private String address1;
    private String address2;
    private String street;
    private String city;
    private User user;
}
