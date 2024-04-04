package com.api.delivery.domain.order;

import com.api.delivery.domain.user.User;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderRegisterDto(@NotBlank
                               String title,
                               @NotNull
                               BigDecimal price,
                               @NotNull
                               @JsonAlias({"amount"})
                               Integer quantity,
                               @NotNull
                               BigDecimal totalPrice,
                               String thumbnail
                               ) {
}
