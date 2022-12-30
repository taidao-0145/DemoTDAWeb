package com.example.demotda.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String supplier;
    private String address;

    @OneToMany(mappedBy = "supplier",cascade =CascadeType.ALL)
    @JsonManagedReference
    private List<Product> products;

    @OneToMany(mappedBy = "supplier",cascade =CascadeType.ALL)
    @JsonManagedReference
    private List<ImportMaster> importMasters;

    public Supplier(String supplier, String address) {
        this.supplier = supplier;
        this.address = address;
    }
}
