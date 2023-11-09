package net.greeta.order.payment.service.domain.ports.input.message.listener;

import net.greeta.order.payment.service.domain.dto.PaymentRequest;

public interface PaymentRequestMessageListener {

    void completePayment(PaymentRequest paymentRequest);

    void cancelPayment(PaymentRequest paymentRequest);
}
