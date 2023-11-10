package net.greeta.order.payment.domain.ports.input.message.listener;

import net.greeta.order.payment.domain.dto.PaymentRequest;

public interface PaymentRequestMessageListener {

    void completePayment(PaymentRequest paymentRequest);

    void cancelPayment(PaymentRequest paymentRequest);
}
