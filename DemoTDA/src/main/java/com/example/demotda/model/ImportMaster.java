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
public class ImportMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateadd;

    private long total;
    private String supplier;

    @OneToMany(mappedBy = "importMaster",cascade =CascadeType.ALL)
    @JsonManagedReference
    private List<ImportProduct> importProducts;

    public ImportMaster(Date dateadd, String supplier,long total) {
        this.total=total;
        this.dateadd = dateadd;
        this.supplier = supplier;
    }
}
