package HarryPotter.HarryPotter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("HarryPotter.HarryPotter.model")
public class HarryPotterApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarryPotterApplication.class, args);
	}

}
