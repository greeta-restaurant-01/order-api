package net.greeta.order.domain;

import net.greeta.order.domain.entity.Order;
import net.greeta.order.domain.entity.Restaurant;
import net.greeta.order.domain.event.OrderCancelledEvent;
import net.greeta.order.domain.event.OrderCreatedEvent;
import net.greeta.order.domain.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}
