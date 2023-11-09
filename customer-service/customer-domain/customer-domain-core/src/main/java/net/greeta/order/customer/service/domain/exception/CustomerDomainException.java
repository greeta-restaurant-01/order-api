package net.greeta.order.customer.service.domain.exception;

import net.greeta.order.domain.exception.DomainException;

public class CustomerDomainException extends DomainException {

    public CustomerDomainException(String message) {
        super(message);
    }
}
