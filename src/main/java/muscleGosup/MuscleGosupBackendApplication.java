package muscleGosup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {"muscleGosup", "muscleGosup.mapper"})
public class MuscleGosupBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuscleGosupBackendApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200") 
						.allowedMethods("GET", "POST", "PUT", "DELETE") 
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}
