package net.greeta.order.customer.domain;


import net.greeta.order.customer.domain.create.CreateCustomerCommand;
import net.greeta.order.customer.domain.event.CustomerCreatedEvent;
import net.greeta.order.customer.domain.mapper.CustomerDataMapper;
import net.greeta.order.customer.domain.ports.input.service.CustomerApplicationService;
import net.greeta.order.customer.domain.ports.output.message.publisher.CustomerMessagePublisher;
import net.greeta.order.customer.domain.create.CreateCustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class CustomerApplicationServiceImpl implements CustomerApplicationService {

    private final CustomerCreateCommandHandler customerCreateCommandHandler;

    private final CustomerDataMapper customerDataMapper;

    private final CustomerMessagePublisher customerMessagePublisher;

    public CustomerApplicationServiceImpl(CustomerCreateCommandHandler customerCreateCommandHandler,
                                          CustomerDataMapper customerDataMapper,
                                          CustomerMessagePublisher customerMessagePublisher) {
        this.customerCreateCommandHandler = customerCreateCommandHandler;
        this.customerDataMapper = customerDataMapper;
        this.customerMessagePublisher = customerMessagePublisher;
    }

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerCommand createCustomerCommand) {
        CustomerCreatedEvent customerCreatedEvent = customerCreateCommandHandler.createCustomer(createCustomerCommand);
        customerMessagePublisher.publish(customerCreatedEvent);
        return customerDataMapper
                .customerToCreateCustomerResponse(customerCreatedEvent.getCustomer(),
                        "Customer saved successfully!");
    }
}
