package net.greeta.order.customer.domain.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateCustomerResponse {
    @NotNull
    private final UUID customerId;
    @NotNull
    private final String message;
}
