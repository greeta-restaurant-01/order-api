package net.greeta.order.restaurant.domain.ports.input.message.listener;

import net.greeta.order.restaurant.domain.dto.RestaurantApprovalRequest;

public interface RestaurantApprovalRequestMessageListener {
    void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest);
}
