package net.greeta.order.payment.domain.ports.output.repository;

import net.greeta.order.common.domain.valueobject.CustomerId;
import net.greeta.order.payment.domain.entity.CreditHistory;

import java.util.List;
import java.util.Optional;

public interface CreditHistoryRepository {

    CreditHistory save(CreditHistory creditHistory);

    Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);
}
