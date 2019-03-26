package com.homics.useractivity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        // Redirect our static resources to their respective classpath (front)
        resourceHandlerRegistry.addResourceHandler("/user/static/**")
                .addResourceLocations("classpath:/public/static/");
        resourceHandlerRegistry.addResourceHandler("/mono/static/**")
                .addResourceLocations("classpath:/public/static/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow CORS for our micro-services
        registry.addMapping("/internal/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("authorization", "content-type", "x-auth-token", "x-xsrf-token")
                .exposedHeaders("x-auth-token", "x-xsrf-token")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
