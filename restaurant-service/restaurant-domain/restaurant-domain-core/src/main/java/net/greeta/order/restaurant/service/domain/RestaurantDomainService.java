package net.greeta.order.restaurant.service.domain;

import net.greeta.order.restaurant.service.domain.entity.Restaurant;
import net.greeta.order.restaurant.service.domain.event.OrderApprovalEvent;

import java.util.List;

public interface RestaurantDomainService {

    OrderApprovalEvent validateOrder(Restaurant restaurant, List<String> failureMessages);
}
