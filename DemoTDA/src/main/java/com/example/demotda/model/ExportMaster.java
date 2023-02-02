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
public class ExportMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date exportDate;
    private long total;
    private String branch;
    private String note;

    @OneToMany(mappedBy = "exportMaster",cascade =CascadeType.ALL)
    @JsonManagedReference
    private List<ExportProduct> exportProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public ExportMaster(Date exportDate, long total, String branch, String note,User user) {
        this.exportDate = exportDate;
        this.total = total;
        this.branch = branch;
        this.note = note;
        this.user=user;
    }


}
