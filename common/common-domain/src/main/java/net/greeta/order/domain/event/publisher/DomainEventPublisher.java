package net.greeta.order.domain.event.publisher;

import net.greeta.order.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
