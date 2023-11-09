package net.greeta.order.outbox;

public interface OutboxScheduler {
    void processOutboxMessage();
}
