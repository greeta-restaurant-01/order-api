package net.greeta.order.domain;

public record Store(String locationId,
                    Address address,
                    String contactNum) {
}
