package com.project.carrot.entity;

import com.project.carrot.entity.locationItem.city.City;
import com.project.carrot.entity.locationItem.town.Town;
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


}
