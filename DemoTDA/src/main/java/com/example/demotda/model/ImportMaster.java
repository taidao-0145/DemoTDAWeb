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
    private long debt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierId")
    private Supplier supplier;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private String note;

    @OneToMany(mappedBy = "importMaster",cascade =CascadeType.ALL)
    @JsonManagedReference
    private List<ImportProduct> importProducts;

    public ImportMaster(Date dateadd, long total, long debt, Supplier supplier, User user, String note) {
        this.dateadd = dateadd;
        this.total = total;
        this.debt = debt;
        this.supplier = supplier;
        this.user = user;
        this.note = note;
    }

    public ImportMaster(Date dateadd, String note, long debt) {
        this.dateadd = dateadd;
        this.note = note;
        this.debt=debt;
    }

}
