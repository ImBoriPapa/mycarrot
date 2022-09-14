package com.project.carrot.web.controller.homeController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")  //template dp index.html
public class HomeController {

//    @GetMapping("/")
    public ModelAndView goToLoginPage(){

        return new ModelAndView("home/home");
    }

    @GetMapping("/")
    public ModelAndView goToApiHome(){

        return new ModelAndView("api/home/api-home");
    }




}
