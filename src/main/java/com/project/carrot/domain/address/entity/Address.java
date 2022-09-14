package com.project.carrot.domain.address.entity;

import com.project.carrot.domain.addressdata.entity.AddressData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Transactional
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;
    private String city;
    private String district;
    private String town;
    private int addressCode;

    @Builder(builderMethodName = "CreateAddress")
    public Address(String city, String district, String town) {
        this.city = city;
        this.district = district;
        this.town = town;
    }

    public Address(AddressData addressData) {
        this.city = addressData.getCity();
        this.district = addressData.getDistrict();
        this.town = addressData.getTown();
        this.addressCode = addressData.getAddressCode();
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", city, district, town);
    }
}
