package com.api.delivery.domain.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderRegisterDto(@NotBlank
                               String title,
                               @NotNull
                               BigDecimal price,
                               @NotNull
                               Integer quantity,
                               @NotNull
                               BigDecimal totalPrice,
                               String thumbnail
//                               Byte thumbnail
                               ) {
}
