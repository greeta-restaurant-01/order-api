package net.greeta.order.customer.domain.ports.input.service;

import net.greeta.order.customer.domain.create.CreateCustomerCommand;
import net.greeta.order.customer.domain.create.CreateCustomerResponse;

import jakarta.validation.Valid;

public interface CustomerApplicationService {

    CreateCustomerResponse createCustomer(@Valid CreateCustomerCommand createCustomerCommand);

}
