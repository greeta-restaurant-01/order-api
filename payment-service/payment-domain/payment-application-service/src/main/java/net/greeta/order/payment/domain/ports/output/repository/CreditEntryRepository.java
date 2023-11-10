package net.greeta.order.payment.domain.ports.output.repository;

import net.greeta.order.common.domain.valueobject.CustomerId;
import net.greeta.order.payment.domain.entity.CreditEntry;

import java.util.Optional;

public interface CreditEntryRepository {

    CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}
