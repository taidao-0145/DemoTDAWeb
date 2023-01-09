package com.example.demotda.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class ReturnProductMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date datereturn;
    private String branch;
    private String note;

    @OneToMany(mappedBy = "returnProductMaster",cascade =CascadeType.ALL)
    @JsonManagedReference
    private List<ReturnProduct> returnProducts;

    public ReturnProductMaster(Date datereturn, String branch, String note) {
        this.datereturn = datereturn;
        this.branch = branch;
        this.note = note;
    }
}
