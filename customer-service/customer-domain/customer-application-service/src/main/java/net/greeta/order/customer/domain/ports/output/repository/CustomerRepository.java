package net.greeta.order.customer.domain.ports.output.repository;

import net.greeta.order.customer.domain.entity.Customer;

public interface CustomerRepository {

    Customer createCustomer(Customer customer);
}
