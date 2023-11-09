package net.greeta.order.restaurant.service.domain.ports.input.message.listener;

import net.greeta.order.restaurant.service.domain.dto.RestaurantApprovalRequest;

public interface RestaurantApprovalRequestMessageListener {
    void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest);
}
