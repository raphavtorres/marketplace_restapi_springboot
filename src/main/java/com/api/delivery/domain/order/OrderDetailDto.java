package com.api.delivery.domain.order;

import com.api.delivery.domain.order.Order;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderDetailDto(UUID id, String title, BigDecimal price, Integer amount, String thumbnail, BigDecimal totalPrice) {
    public OrderDetailDto(Order order) {
        this(order.getId(), order.getTitle(), order.getPrice(), order.getQuantity(), order.getThumbnail(), order.getTotalPrice());
    }
}
