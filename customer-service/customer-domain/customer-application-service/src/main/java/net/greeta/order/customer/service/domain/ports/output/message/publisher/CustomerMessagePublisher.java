package net.greeta.order.customer.service.domain.ports.output.message.publisher;

import net.greeta.order.customer.service.domain.event.CustomerCreatedEvent;

public interface CustomerMessagePublisher {

    void publish(CustomerCreatedEvent customerCreatedEvent);

}