package dio.design_patterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DioDesignPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DioDesignPatternsApplication.class, args);
	}

}
