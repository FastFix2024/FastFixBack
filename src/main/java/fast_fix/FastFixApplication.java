package fast_fix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FastFixApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastFixApplication.class, args);
	}

}