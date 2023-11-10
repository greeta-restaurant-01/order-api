package net.greeta.order.domain.valueobject;

import net.greeta.order.common.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}
