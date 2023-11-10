package net.greeta.order.restaurant.domain.ports.output.message.publisher;

import net.greeta.order.outbox.OutboxStatus;
import net.greeta.order.restaurant.domain.outbox.model.OrderOutboxMessage;

import java.util.function.BiConsumer;

public interface RestaurantApprovalResponseMessagePublisher {

    void publish(OrderOutboxMessage orderOutboxMessage,
                 BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback);
}
