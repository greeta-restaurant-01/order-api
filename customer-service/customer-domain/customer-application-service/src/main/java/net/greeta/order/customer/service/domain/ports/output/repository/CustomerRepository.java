package net.greeta.order.customer.service.domain.ports.output.repository;

import net.greeta.order.customer.service.domain.entity.Customer;

public interface CustomerRepository {

    Customer createCustomer(Customer customer);
}
