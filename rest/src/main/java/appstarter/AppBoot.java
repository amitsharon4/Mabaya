package appstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class AppBoot{
    public static void main(String[] args) {
        SpringApplication.run(AppBoot.class, args);
    }
}
