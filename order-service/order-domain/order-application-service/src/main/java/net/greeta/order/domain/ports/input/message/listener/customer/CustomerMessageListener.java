package net.greeta.order.domain.ports.input.message.listener.customer;

import net.greeta.order.domain.dto.message.CustomerModel;

public interface CustomerMessageListener {

    void customerCreated(CustomerModel customerModel);
}
