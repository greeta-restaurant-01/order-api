package net.greeta.order.common.domain.event.publisher;

import net.greeta.order.common.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
