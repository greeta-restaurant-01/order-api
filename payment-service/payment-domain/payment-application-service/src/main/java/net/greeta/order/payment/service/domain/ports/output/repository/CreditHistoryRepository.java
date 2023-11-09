package net.greeta.order.payment.service.domain.ports.output.repository;

import net.greeta.order.domain.valueobject.CustomerId;
import net.greeta.order.payment.service.domain.entity.CreditHistory;

import java.util.List;
import java.util.Optional;

public interface CreditHistoryRepository {

    CreditHistory save(CreditHistory creditHistory);

    Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);
}
