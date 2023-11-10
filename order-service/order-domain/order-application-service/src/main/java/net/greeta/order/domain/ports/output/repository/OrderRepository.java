package net.greeta.order.domain.ports.output.repository;

import net.greeta.order.common.domain.valueobject.OrderId;
import net.greeta.order.domain.entity.Order;
import net.greeta.order.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(OrderId orderId);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
