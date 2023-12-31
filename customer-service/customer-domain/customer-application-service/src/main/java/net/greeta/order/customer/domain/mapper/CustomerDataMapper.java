package net.greeta.order.customer.domain.mapper;

import net.greeta.order.customer.domain.create.CreateCustomerCommand;
import net.greeta.order.customer.domain.create.CreateCustomerResponse;
import net.greeta.order.customer.domain.entity.Customer;
import net.greeta.order.common.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataMapper {

    public Customer createCustomerCommandToCustomer(CreateCustomerCommand createCustomerCommand) {
        return new Customer(new CustomerId(createCustomerCommand.getCustomerId()),
                createCustomerCommand.getUsername(),
                createCustomerCommand.getFirstName(),
                createCustomerCommand.getLastName());
    }

    public CreateCustomerResponse customerToCreateCustomerResponse(Customer customer, String message) {
        return new CreateCustomerResponse(customer.getId().getValue(), message);
    }
}
