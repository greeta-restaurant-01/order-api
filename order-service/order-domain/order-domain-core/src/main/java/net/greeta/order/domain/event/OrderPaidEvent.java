package net.greeta.order.domain.event;

import net.greeta.order.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent {
    public OrderPaidEvent(Order order,
                          ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
