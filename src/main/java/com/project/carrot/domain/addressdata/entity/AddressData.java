package com.project.carrot.domain.addressdata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name ="ADDRESS_DATA_BASE")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressData {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ADDRESS_DATA_ID")
    private Long id;
    private String city;
    private String district;
    private String town;

    @Column(name = "ADDRESS_CODE")
    private int addressCode;

    public AddressData(String city, String district, String town) {
        this.city = city;
        this.district = district;
        this.town = town;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", city, district, town);
    }
}
