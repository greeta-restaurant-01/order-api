package net.greeta.order.payment.service.domain.ports.output.repository;

import net.greeta.order.domain.valueobject.CustomerId;
import net.greeta.order.payment.service.domain.entity.CreditEntry;

import java.util.Optional;

public interface CreditEntryRepository {

    CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}
