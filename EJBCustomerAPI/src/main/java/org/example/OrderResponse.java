package org.example;

import javax.validation.constraints.NotNull;

public record OrderResponse(@NotNull Long id, @NotNull Long customerId, String status, int itemCount) {

}
