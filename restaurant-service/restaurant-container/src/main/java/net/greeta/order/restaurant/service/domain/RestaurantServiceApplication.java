package net.greeta.order.restaurant.service.domain;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "net.greeta.order.restaurant.service.dataaccess", "net.greeta.order.dataaccess" })
@EntityScan(basePackages = { "net.greeta.order.restaurant.service.dataaccess", "net.greeta.order.dataaccess" })
@SpringBootApplication(scanBasePackages = "net.greeta.order")
public class RestaurantServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceApplication.class, args);
    }
}
