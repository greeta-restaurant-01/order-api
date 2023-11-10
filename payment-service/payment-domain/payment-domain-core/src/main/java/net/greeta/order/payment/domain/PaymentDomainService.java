package net.greeta.order.payment.domain;

import net.greeta.order.payment.domain.entity.CreditEntry;
import net.greeta.order.payment.domain.entity.Payment;
import net.greeta.order.payment.domain.event.PaymentEvent;
import net.greeta.order.payment.domain.entity.CreditHistory;

import java.util.List;

public interface PaymentDomainService {

    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages);

    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessages);
}
