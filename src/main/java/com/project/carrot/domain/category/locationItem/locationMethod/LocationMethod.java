package com.project.carrot.domain.category.locationItem.locationMethod;

import com.project.carrot.domain.category.locationItem.district.*;
import com.project.carrot.domain.category.locationItem.city.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class LocationMethod {



    public void towns(){

    }

    public Districts[] districts(City city){  //City의 요소로 검색후 District.values 반환

        if(city == City.SEOUL){
            return SeoulDistrict.values();
        }else if(city == City.BUSAN){
            return BusanDistrict.values();
        }else if(city == City.DAEGU){
            return DaeguDistrict.values();
        }else if(city == City.GWANGJU){
            return GwangjuDistrict.values();
        }else if(city == City.INCHEON){
            return IncheonDistrict.values();
        }else if(city == City.DAEJEON){
            return DaejeonDistrict.values();
        }else if(city == City.ULSAN){
            return UlsanDistrict.values();
        }else if(city == City.SEJONG){
            return SejongDistrict.values();
        }else if(city == City.GYEONGGI_DO){
            return GyeonggiDoDistrict.values();
        }else if(city == City.GANGWON_DO){
            return GangwonDoDistrict.values();
        }else if(city == City.CHUNGCHEONGNAM_DO){
            return ChungcheongNamDoDistrict.values();
        }else if(city == City.CHUNGCHEONGBUK_DO){
            return ChungcheongBukDoDistrict.values();
        }else if(city == City.JEOLLANAM_DO){
            return JeollanamDoDistrict.values();
        }else if(city == City.JEOLLBUK_DO){
            return JeollaBukDoDistrict.values();
        }else if(city == City.GYEONGSANGNAM_DO){
            return GyeongsangNamDoDistrict.values();
        }else if(city == City.GYEONGSANGBUK_DO){
            return GyeongsangBukDoDistrict.values();
        }else if(city == City.JEJU_DO){
            return JejuDoDistrict.values();
        }else{
            return null;
        }

    }
}
