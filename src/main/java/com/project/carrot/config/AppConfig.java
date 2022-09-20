package com.project.carrot.config;

import com.project.carrot.utlis.converter.ItemCategoryConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig  {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ItemCategoryConverter itemCategoryConverter(){
        return new ItemCategoryConverter();
    }

}
