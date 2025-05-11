package com.hydroneo.devicesRegistry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // allow all paths
                        .allowedOrigins(
                            "http://localhost:4200", 
                            "http://localhost:8090",
                            "http://localhost:8081", //from app aqua core
                            "http://device-registry-alb-1-651558055.ap-southeast-1.elb.amazonaws.com",
                            "http://device-registry-alb-frontend-2-1233803147.ap-southeast-1.elb.amazonaws.com"
                        ) 
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // allow these HTTP methods
                        .allowedHeaders("*") // allow all headers
                        .allowCredentials(true); // allow cookies/auth if needed
            }
        };
    }
}
