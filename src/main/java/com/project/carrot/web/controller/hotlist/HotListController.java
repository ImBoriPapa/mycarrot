package com.project.carrot.web.controller.hotlist;

import com.project.carrot.web.dto.LocationDto;

import com.project.carrot.domain.category.locationItem.city.City;
import com.project.carrot.domain.category.locationItem.district.Districts;
import com.project.carrot.domain.category.locationItem.locationMethod.LocationMethod;
import com.project.carrot.domain.category.locationItem.town.Town;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HotListController {

    private final LocationMethod locationMethod;

    @ModelAttribute("cities")
    public City[] city(){
        return City.values();
    }

    @GetMapping("/hotList")
    public String hotList(Model model) {

        LocationDto locationDTO = new LocationDto();

        model.addAttribute("locationDTO",locationDTO );

        return "hotList/hotList";
    }

    @GetMapping("/hotList/{city}")
    public String hotList2(@PathVariable City city, Model model) {



        System.out.println("city = " + city.name());
        LocationMethod locationMethod = new LocationMethod();
        Districts[] districts = locationMethod.districts(city);

        model.addAttribute("cityName",city.name());
        model.addAttribute("districts",districts);

        return "hotList/hotList";
    }

    @GetMapping("/hotList/{city}/{district}")
    public String hotList3(@PathVariable City city,@PathVariable String district,Model model) {



        System.out.println("city = " + city.name());
        System.out.println("district = " + district);

        LocationMethod locationMethod = new LocationMethod();
        Districts[] districts = locationMethod.districts(city);
        Town[] towns = Town.values();

        model.addAttribute("districts",districts);


        return "hotList/hotList";
    }
}
