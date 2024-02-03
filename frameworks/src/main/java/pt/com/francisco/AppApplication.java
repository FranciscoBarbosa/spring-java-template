package pt.com.francisco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
            "pt.com.francisco.web",
            "pt.com.francisco.launchers",
            "pt.com.francisco.dataaccess"
        })
@EnableJpaRepositories(basePackages = "pt.com.francisco.dataaccess")
@EntityScan(basePackages = "pt.com.francisco.dataaccess")
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
