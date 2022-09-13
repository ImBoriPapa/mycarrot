package com.project.carrot.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    public void test(){
      log.info("test");
    }

    @GetMapping("/deny")
    public void deny(){
        log.info("deny");
    }

}
