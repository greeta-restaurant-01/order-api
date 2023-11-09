package net.greeta.order.restaurant.service.domain.ports.output.repository;

import net.greeta.order.restaurant.service.domain.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
