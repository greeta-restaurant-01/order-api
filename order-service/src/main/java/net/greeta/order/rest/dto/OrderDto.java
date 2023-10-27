package net.greeta.order.rest.dto;

import java.time.ZonedDateTime;

public record OrderDto(String id, String description, UserDto user, ZonedDateTime createdAt) {

    public record UserDto(String username) {
    }
}