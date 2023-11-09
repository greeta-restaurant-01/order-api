package net.greeta.order.payment.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "net.greeta.order.payment.service.dataaccess")
@EntityScan(basePackages = "net.greeta.order.payment.service.dataaccess")
@SpringBootApplication(scanBasePackages = "net.greeta.order")
public class PaymentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
