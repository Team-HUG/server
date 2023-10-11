package com.hub.demo.global.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("/**")
                .allowedMethods("POST", "GET")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}