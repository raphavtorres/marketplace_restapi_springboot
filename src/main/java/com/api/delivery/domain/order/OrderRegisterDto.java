package com.api.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderRegisterDto(@NotBlank
                               String title,
                               @NotNull
                               BigDecimal price,
                               @NotNull
                               Integer amount,
                               String thumbnail
//                               Byte thumbnail
                               ) {
}
