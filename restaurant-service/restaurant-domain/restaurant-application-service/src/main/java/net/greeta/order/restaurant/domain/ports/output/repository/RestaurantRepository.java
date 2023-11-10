package net.greeta.order.restaurant.domain.ports.output.repository;

import net.greeta.order.restaurant.domain.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
