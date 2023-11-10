package net.greeta.order.domain.ports.output.message.publisher.payment;

import net.greeta.order.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import net.greeta.order.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface PaymentRequestMessagePublisher {

    void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
                 BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback);
}
