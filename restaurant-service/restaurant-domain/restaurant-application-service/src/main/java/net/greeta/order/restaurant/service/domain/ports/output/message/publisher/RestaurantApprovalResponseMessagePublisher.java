package net.greeta.order.restaurant.service.domain.ports.output.message.publisher;

import net.greeta.order.outbox.OutboxStatus;
import net.greeta.order.restaurant.service.domain.outbox.model.OrderOutboxMessage;

import java.util.function.BiConsumer;

public interface RestaurantApprovalResponseMessagePublisher {

    void publish(OrderOutboxMessage orderOutboxMessage,
                 BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback);
}
