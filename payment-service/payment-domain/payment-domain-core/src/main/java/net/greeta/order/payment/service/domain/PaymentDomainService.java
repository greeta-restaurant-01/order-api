package net.greeta.order.payment.service.domain;

import net.greeta.order.payment.service.domain.entity.CreditEntry;
import net.greeta.order.payment.service.domain.entity.CreditHistory;
import net.greeta.order.payment.service.domain.entity.Payment;
import net.greeta.order.payment.service.domain.event.PaymentEvent;

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
