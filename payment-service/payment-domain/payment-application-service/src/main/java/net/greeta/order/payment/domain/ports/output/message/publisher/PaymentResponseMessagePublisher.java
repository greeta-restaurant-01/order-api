package net.greeta.order.payment.domain.ports.output.message.publisher;

import net.greeta.order.outbox.OutboxStatus;
import net.greeta.order.payment.domain.outbox.model.OrderOutboxMessage;

import java.util.function.BiConsumer;

public interface PaymentResponseMessagePublisher {
    void publish(OrderOutboxMessage orderOutboxMessage,
                 BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback);
}
