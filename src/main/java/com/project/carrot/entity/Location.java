package com.project.carrot.entity;

import com.project.carrot.entity.locationItem.district.District;
import com.project.carrot.entity.locationItem.district.SeoulDistrict;
import com.project.carrot.entity.locationItem.City;
import com.project.carrot.entity.locationItem.town.GangNamTown;
import com.project.carrot.entity.locationItem.town.Town;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Location {

    @Enumerated(EnumType.STRING)
    private City city;
    @Enumerated(EnumType.STRING)
    private District district;
    @Enumerated(EnumType.STRING)
    private Town town;

    public Location() {}

    public Location(City city, District district, Town town) {
        this.city = city;
        this.district = district;
        this.town = town;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
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
