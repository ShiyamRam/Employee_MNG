package net.javaguides.springboot.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow CORS on all 
        .allowedOrigins("*") // Allow requests from Angular's URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow necessary methods
                .allowedHeaders("*") ;// Allow all headers
//                .allowCredentials(true);
    }	

}
