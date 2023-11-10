package net.greeta.order.domain.ports.input.service;

import net.greeta.order.domain.dto.create.CreateOrderCommand;
import net.greeta.order.domain.dto.create.CreateOrderResponse;
import net.greeta.order.domain.dto.track.TrackOrderQuery;
import net.greeta.order.domain.dto.track.TrackOrderResponse;

import jakarta.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
