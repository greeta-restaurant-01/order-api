package net.greeta.order.service.application.rest;

import net.greeta.order.domain.dto.create.CreateOrderCommand;
import net.greeta.order.domain.dto.create.CreateOrderResponse;
import net.greeta.order.domain.dto.track.TrackOrderQuery;
import net.greeta.order.domain.dto.track.TrackOrderResponse;
import net.greeta.order.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        log.info("Creating order for customer: {} at restaurant: {}", createOrderCommand.getCustomerId(),
                createOrderCommand.getRestaurantId());
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
        log.info("Order created with tracking id: {}", createOrderResponse.getOrderTrackingId());
        return ResponseEntity.ok(createOrderResponse);
    }

    @GetMapping("/{trackingId}")
    public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@PathVariable UUID trackingId) {
       TrackOrderResponse trackOrderResponse =
               orderApplicationService.trackOrder(TrackOrderQuery.builder().orderTrackingId(trackingId).build());
       log.info("Returning order status with tracking id: {}", trackOrderResponse.getOrderTrackingId());
       return  ResponseEntity.ok(trackOrderResponse);
    }
}
