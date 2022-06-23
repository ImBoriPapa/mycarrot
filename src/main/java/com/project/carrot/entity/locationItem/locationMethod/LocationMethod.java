package com.project.carrot.entity.locationItem.locationMethod;

import com.project.carrot.entity.locationItem.city.City;
import com.project.carrot.entity.locationItem.district.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class LocationMethod {



    public void districtName(List<Districts> districtsList, String choose){
        Stream<Districts> stream = districtsList.stream();
        Stream<Districts> towns = stream.filter(town -> town.equals(choose));
        System.out.println("towns = " + towns);
    }



    public List<Districts> districtList(City city){  //City의 요소로 검색후 List로 반환 district -> List

        if(city.equals(City.SEOUL)){
            Stream<Districts> stream = Arrays.stream(SeoulDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.BUSAN)){
            Stream<Districts> stream = Arrays.stream(BusanDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.DAEGU)){
            Stream<Districts> stream = Arrays.stream(DaeguDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.GWANGJU)){
            Stream<Districts> stream = Arrays.stream(GwangjuDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.INCHEON)){
            Stream<Districts> stream = Arrays.stream(IncheonDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.DAEJEON)){
            Stream<Districts> stream = Arrays.stream(DaejeonDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.ULSAN)){
            Stream<Districts> stream = Arrays.stream(UlsanDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.SEJONG)){
            Stream<Districts> stream = Arrays.stream(SeoulDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.GYEONGGI_DO)){
            Stream<Districts> stream = Arrays.stream(GyeonggiDoDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.GANGWON_DO)){
            Stream<Districts> stream = Arrays.stream(GangwonDoDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.CHUNGCHEONGNAM_DO)){
            Stream<Districts> stream = Arrays.stream(ChungcheongNamDoDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.CHUNGCHEONGBUK_DO)){
            Stream<Districts> stream = Arrays.stream(ChungcheongBukDoDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.JEOLLANAM_DO)){
            Stream<Districts> stream = Arrays.stream(JeollanamDoDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.JEOLLBUK_DO)){
            Stream<Districts> stream = Arrays.stream(JeollaBukDoDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.GYEONGSANGNAM_DO)){
            Stream<Districts> stream = Arrays.stream(GyeongsangNamDoDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.GYEONGSANGBUK_DO)){
            Stream<Districts> stream = Arrays.stream(GyeongsangBukDoDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else if(city.equals(City.JEJU_DO)){
            Stream<Districts> stream = Arrays.stream(JejuDoDistrict.values());
            List<Districts> collect = stream.collect(Collectors.toList());
            return collect;
        }else{
            return null;
        }

    }
}
