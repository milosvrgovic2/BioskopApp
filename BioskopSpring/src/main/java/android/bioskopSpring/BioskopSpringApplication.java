package android.bioskopSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("model")
@ComponentScan(basePackages = "android.bioskopSpring.controller")
@EnableJpaRepositories(basePackages = "android.bioskopSpring.repository")
public class BioskopSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BioskopSpringApplication.class, args);
	}

}
