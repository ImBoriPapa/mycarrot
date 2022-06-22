package com.project.carrot.entity;

import com.project.carrot.entity.locationItem.city.City;
import com.project.carrot.entity.locationItem.district.Districts;
import com.project.carrot.entity.locationItem.locationMethod.LocationMethod;
import org.junit.jupiter.api.Test;

import java.util.List;

class LocationTest {

    @Test
    public void search() throws Exception
    {
        //given
        LocationMethod locationMethod = new LocationMethod();



        List<Districts> districts = locationMethod.districtList(City.SEOUL);
        for (Districts district : districts) {
            System.out.println("district = " + district);
        }


        //when

        //then

    }
}