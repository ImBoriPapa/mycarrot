package com.project.carrot.domain.location;

import com.project.carrot.domain.category.locationItem.city.City;
import com.project.carrot.domain.category.locationItem.town.Town;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Embeddable
public class Location {

    @Enumerated(EnumType.STRING)
    private City city;
    @Enumerated(EnumType.STRING)
    private Enum district;
    @Enumerated(EnumType.STRING)
    private Town town;


    public Location() {}

    public Location(City city, Enum district, Town town) {
        this.city = city;
        this.district = district;
        this.town = town;
    }
}
