package net.greeta.order.customer.domain.exception;

import net.greeta.order.common.domain.exception.DomainException;

public class CustomerDomainException extends DomainException {

    public CustomerDomainException(String message) {
        super(message);
    }
}
