package net.greeta.order.domain;

public record OrderCountPerStoreDTO(String locationId,
                                    Long orderCount) {
}
