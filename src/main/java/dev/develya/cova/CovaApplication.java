package dev.develya.cova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CovaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovaApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsMappingConfigurer() { // Bean to configure CORS globally
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Allow frontend origins
						.allowedOrigins("http://localhost:3000", "http://192.168.240.100:3000");
			}
		};
	}
}
