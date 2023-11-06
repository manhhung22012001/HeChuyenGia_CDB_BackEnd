package com.Hechuyengia.Chuandoanbenh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ChuandoanbenhApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChuandoanbenhApplication.class, args);
	}
        @Configuration
    @EnableWebMvc
    public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200") // your angular URL
                    .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE")
                    .allowedHeaders("Content-Type", "Authorization") // Adjust headers you need to allow
                    .allowCredentials(true); // Add only if you want to access cookie
        }

    }
}
