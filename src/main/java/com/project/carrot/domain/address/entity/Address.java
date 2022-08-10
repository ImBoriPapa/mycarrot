package com.project.carrot.domain.address.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name ="ADDRESS")
@Getter
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ADDRESS_ID")
    private Long id;
    private String district;
    private String town;
    private String dong;

    public Address() {
    }

    public Address(String district, String town, String dong) {
        this.district = district;
        this.town = town;
        this.dong = dong;
    }
}
