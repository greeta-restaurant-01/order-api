package net.greeta.order.restaurant.service.domain.ports.output.repository;

import net.greeta.order.restaurant.service.domain.entity.OrderApproval;

public interface OrderApprovalRepository {
    OrderApproval save(OrderApproval orderApproval);
}
