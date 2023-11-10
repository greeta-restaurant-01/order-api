package net.greeta.order.customer.domain;

import net.greeta.order.customer.domain.entity.Customer;
import net.greeta.order.customer.domain.event.CustomerCreatedEvent;

public interface CustomerDomainService {

    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);

}
