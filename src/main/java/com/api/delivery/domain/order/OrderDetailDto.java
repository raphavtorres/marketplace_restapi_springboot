package com.api.delivery.dto;

import com.api.delivery.model.Order;

import java.math.BigDecimal;

public record OrderDetailDto(Long id, String title, BigDecimal price, Integer amount, String thumbnail) {
    public OrderDetailDto(Order order) {
        this(order.getId(), order.getTitle(), order.getPrice(), order.getAmount(), order.getThumbnail());
    }
}
