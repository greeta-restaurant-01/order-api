package net.greeta.order.restaurant.domain.ports.output.repository;

import net.greeta.order.restaurant.domain.entity.OrderApproval;

public interface OrderApprovalRepository {
    OrderApproval save(OrderApproval orderApproval);
}
