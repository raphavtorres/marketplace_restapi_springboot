package com.api.delivery.domain.order;

import com.api.delivery.domain.order.Order;

import java.math.BigDecimal;

public record OrderDetailDto(String id, String title, BigDecimal price, Integer amount, String thumbnail, BigDecimal totalPrice) {
    public OrderDetailDto(Order order) {
        this(order.getId(), order.getTitle(), order.getPrice(), order.getQuantity(), order.getThumbnail(), order.getTotalPrice());
    }
}
