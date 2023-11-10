package net.greeta.order.domain.ports.output.message.publisher.restaurantapproval;

import net.greeta.order.domain.outbox.model.approval.OrderApprovalOutboxMessage;
import net.greeta.order.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface RestaurantApprovalRequestMessagePublisher {

    void publish(OrderApprovalOutboxMessage orderApprovalOutboxMessage,
                 BiConsumer<OrderApprovalOutboxMessage, OutboxStatus> outboxCallback);
}
