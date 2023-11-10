package net.greeta.order.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = { "net.greeta.order.customer.dataaccess", "net.greeta.order.common.dataaccess"})
@EntityScan(basePackages = { "net.greeta.order.customer.dataaccess", "net.greeta.order.common.dataaccess" })
@SpringBootApplication(scanBasePackages = "net.greeta.order")
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
