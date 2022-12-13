package com.example.demotda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String address1;
    private String address2;
    private String street;
    private String city;


    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable=false, referencedColumnName = "id")
    @JsonBackReference
    private User user;

    public UserProfile(String fullName,String address1, String address2, String street, String city) {
        this.fullName=fullName;
        this.address1 = address1;
        this.address2 = address2;
        this.street = street;
        this.city = city;
    }

    public UserProfile(Long id, String fullName, String address1, String address2, String street, String city, User user) {
        this.id = id;
        this.fullName = fullName;
        this.address1 = address1;
        this.address2 = address2;
        this.street = street;
        this.city = city;
        this.user = user;
    }
}
