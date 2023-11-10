package net.greeta.order.customer.domain.ports.output.message.publisher;

import net.greeta.order.customer.domain.event.CustomerCreatedEvent;

public interface CustomerMessagePublisher {

    void publish(CustomerCreatedEvent customerCreatedEvent);

}