package pt.com.francisco.launchers.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"pt.com.francisco.web",
		"pt.com.francisco.launchers",
		"pt.com.francisco.dataAccess"
})
@EnableJpaRepositories(basePackages = "pt.com.francisco.dataAccess")
@EntityScan(basePackages = "pt.com.francisco.dataAccess")
public class AppApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
