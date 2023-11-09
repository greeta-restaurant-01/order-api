package net.greeta.order.customer.service.domain;

import net.greeta.order.customer.service.domain.entity.Customer;
import net.greeta.order.customer.service.domain.event.CustomerCreatedEvent;

public interface CustomerDomainService {

    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);

}
