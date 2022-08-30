package com.project.carrot.domain.address.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name ="ADDRESS")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ADDRESS_ID")
    private Long id;
    private String city;
    private String district;
    private String town;

    @Column(name = "TOWN_NUMBER")
    private Long townNumber;

    public Address(String city, String district, String town) {
        this.city = city;
        this.district = district;
        this.town = town;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", city, district, town);
    }
}
