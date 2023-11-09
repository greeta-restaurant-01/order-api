package net.greeta.order.restaurant.service.domain.valueobject;

import net.greeta.order.domain.valueobject.BaseId;

import java.util.UUID;

public class OrderApprovalId extends BaseId<UUID> {
    public OrderApprovalId(UUID value) {
        super(value);
    }
}
