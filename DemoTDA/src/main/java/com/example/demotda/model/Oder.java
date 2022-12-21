package com.example.demotda.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


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

    private String address;

    private Long idproduct;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date date;

    private int total;
    private int idstatus;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "cartId", nullable=false)
    @JsonBackReference
    private Cart cart;



}
