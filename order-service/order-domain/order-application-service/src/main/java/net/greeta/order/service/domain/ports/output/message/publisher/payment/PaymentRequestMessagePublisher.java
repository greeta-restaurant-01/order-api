package net.greeta.order.service.domain.ports.output.message.publisher.payment;

import net.greeta.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import net.greeta.order.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface PaymentRequestMessagePublisher {

    void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
                 BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback);
}
