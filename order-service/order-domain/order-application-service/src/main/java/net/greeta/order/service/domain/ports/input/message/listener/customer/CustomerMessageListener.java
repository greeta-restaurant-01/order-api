package net.greeta.order.service.domain.ports.input.message.listener.customer;

import net.greeta.order.service.domain.dto.message.CustomerModel;

public interface CustomerMessageListener {

    void customerCreated(CustomerModel customerModel);
}
