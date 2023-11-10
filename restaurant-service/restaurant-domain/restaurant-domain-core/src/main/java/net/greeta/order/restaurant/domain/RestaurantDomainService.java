package net.greeta.order.restaurant.domain;

import net.greeta.order.restaurant.domain.entity.Restaurant;
import net.greeta.order.restaurant.domain.event.OrderApprovalEvent;

import java.util.List;

public interface RestaurantDomainService {

    OrderApprovalEvent validateOrder(Restaurant restaurant, List<String> failureMessages);
}
