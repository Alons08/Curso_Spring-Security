package com.tcna.primeraweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer{

    //Este metodo es para configurar vistas
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/403.html").setViewName("403.html");
    }

}


