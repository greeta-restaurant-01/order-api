package net.greeta.order.service.domain.ports.output.repository;

import net.greeta.order.domain.valueobject.OrderId;
import net.greeta.order.service.domain.entity.Order;
import net.greeta.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(OrderId orderId);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
