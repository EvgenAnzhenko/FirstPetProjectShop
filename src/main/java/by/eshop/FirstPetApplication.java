package by.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "by.eshop.repository")
public class FirstPetApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstPetApplication.class, args);
    }

}
