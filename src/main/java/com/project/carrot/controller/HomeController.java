package com.project.carrot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")  //template dp index.html
public class HomeController {

    @GetMapping("/")
    public String goToLoginPage(){

        return "/member/loginPage";
    }

}
