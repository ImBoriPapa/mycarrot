package com.project.carrot.entity;

import com.project.carrot.entity.locationItem.city.City;
import com.project.carrot.entity.locationItem.district.Districts;
import com.project.carrot.entity.locationItem.district.SeoulDistrict;
import com.project.carrot.entity.locationItem.locationMethod.LocationMethod;
import org.junit.jupiter.api.Test;

import java.util.List;

class LocationTest {
    @Test
    public void method() throws Exception
    {
        //given


        //when

        //then

    };

    @Test
    public void district() throws Exception
    {
        //given
        LocationMethod locationMethod = new LocationMethod();


        Districts[] districts = locationMethod.districts(City.CHUNGCHEONGBUK_DO);
        for (Districts district : districts) {
            System.out.println("district = " + district);
        }




        //when

        //then

    }



    @Test
    public void search() throws Exception
    {
        //given
        LocationMethod locationMethod = new LocationMethod();

        City[] cities = City.values();
        for (City city : cities) {
            System.out.println("city = " + city);
        }
        //when

        //then

    }
}