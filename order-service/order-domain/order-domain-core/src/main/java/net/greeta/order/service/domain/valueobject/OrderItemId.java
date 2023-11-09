package net.greeta.order.service.domain.valueobject;

import net.greeta.order.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}
