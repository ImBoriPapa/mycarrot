package com.project.carrot.web.controller;

import com.project.carrot.domain.category.category.ItemCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")  //template dp index.html
public class HomeController {

    @GetMapping("/")
    public String goToLoginPage(){

        return "/home/home";
    }

    @ModelAttribute("itemCategory")
    public ItemCategory[] itemCategories() {
        return ItemCategory.values();
    }

}
