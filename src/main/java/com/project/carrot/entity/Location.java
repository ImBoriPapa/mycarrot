package com.project.carrot.entity;

import com.project.carrot.entity.locationItem.city.CityList;
import com.project.carrot.entity.locationItem.district.District;
import com.project.carrot.entity.locationItem.town.Town;


import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Location {

    @Enumerated(EnumType.STRING)
    private CityList city;
    @Enumerated(EnumType.STRING)
    private District district;
    @Enumerated(EnumType.STRING)
    private Town town;



    public Location() {}

    public Location(CityList city, District district, Town town) {
        this.city = city;
        this.district = district;
        this.town = town;
    }

    public CityList getCity() {
        return city;
    }

    public void setCity(CityList city) {
        this.city = city;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }



}
