package net.greeta.order.restaurant.domain.mapper;

import net.greeta.order.common.domain.valueobject.Money;
import net.greeta.order.common.domain.valueobject.OrderId;
import net.greeta.order.common.domain.valueobject.OrderStatus;
import net.greeta.order.common.domain.valueobject.RestaurantId;
import net.greeta.order.restaurant.domain.entity.OrderDetail;
import net.greeta.order.restaurant.domain.entity.Restaurant;
import net.greeta.order.restaurant.domain.outbox.model.OrderEventPayload;
import net.greeta.order.restaurant.domain.dto.RestaurantApprovalRequest;
import net.greeta.order.restaurant.domain.entity.Product;
import net.greeta.order.restaurant.domain.event.OrderApprovalEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataMapper {
    public Restaurant restaurantApprovalRequestToRestaurant(RestaurantApprovalRequest
                                                                             restaurantApprovalRequest) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(UUID.fromString(restaurantApprovalRequest.getRestaurantId())))
                .orderDetail(OrderDetail.builder()
                        .orderId(new OrderId(UUID.fromString(restaurantApprovalRequest.getOrderId())))
                        .products(restaurantApprovalRequest.getProducts().stream().map(
                                product -> Product.builder()
                                        .productId(product.getId())
                                        .quantity(product.getQuantity())
                                        .build())
                                .collect(Collectors.toList()))
                        .totalAmount(new Money(restaurantApprovalRequest.getPrice()))
                        .orderStatus(OrderStatus.valueOf(restaurantApprovalRequest.getRestaurantOrderStatus().name()))
                        .build())
                .build();
    }

    public OrderEventPayload
    orderApprovalEventToOrderEventPayload(OrderApprovalEvent orderApprovalEvent) {
        return OrderEventPayload.builder()
                .orderId(orderApprovalEvent.getOrderApproval().getOrderId().getValue().toString())
                .restaurantId(orderApprovalEvent.getRestaurantId().getValue().toString())
                .orderApprovalStatus(orderApprovalEvent.getOrderApproval().getApprovalStatus().name())
                .createdAt(orderApprovalEvent.getCreatedAt())
                .failureMessages(orderApprovalEvent.getFailureMessages())
                .build();
    }
}
