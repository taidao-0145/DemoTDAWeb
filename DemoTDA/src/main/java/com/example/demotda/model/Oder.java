package com.example.demotda.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String city;
    private Long idproduct;
    private int quantity;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date date;
    private String nameproduct;
    private String img;
    private int total;

    private int idstatus;

    public Oder(String username, String fullname, String email, String phone,
                String address, String city, Long idproduct, int quantity,
                Date date,String nameproduct,String img, int total,int idstatus) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.idproduct = idproduct;
        this.quantity = quantity;
        this.date = date;
        this.nameproduct=nameproduct;
        this.img=img;
        this.total=total;
        this.idstatus=idstatus;
    }
}
