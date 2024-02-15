package pt.com.francisco.frameworks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"pt.com.francisco.frameworks"})
@EnableJpaRepositories(basePackages = "pt.com.francisco.frameworks.dataaccess")
@EntityScan(basePackages = "pt.com.francisco.frameworks.dataaccess")
@EnableCaching
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
