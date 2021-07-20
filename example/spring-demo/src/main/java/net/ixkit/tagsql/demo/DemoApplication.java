package net.ixkit.tagsql.demo;

import net.ixkit.tagsql.springboot.config.meta.EnableTagSQLAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTagSQLAutoConfig
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}