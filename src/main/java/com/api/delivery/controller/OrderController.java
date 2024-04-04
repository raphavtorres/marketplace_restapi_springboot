package com.api.delivery.controller;

import com.api.delivery.domain.order.Order;
import com.api.delivery.domain.order.OrderDetailDto;
import com.api.delivery.domain.order.OrderRegisterDto;
import com.api.delivery.domain.user.User;
import com.api.delivery.domain.user.UserRole;
import com.api.delivery.repository.OrderRepository;
import com.api.delivery.service.OrderService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid OrderRegisterDto orderRegisterDto, UriComponentsBuilder uriComponentsBuilder) {
        OrderDetailDto orderDetailDto = orderService.createOrder(orderRegisterDto);

        var uri = uriComponentsBuilder.path("/orders/{id}").build(orderDetailDto.id());
        return ResponseEntity.created(uri).body(orderDetailDto);
    }

    @GetMapping
    public ResponseEntity<Page<OrderDetailDto>> list(Pageable pagination) {
        return orderService.GetOrderByAuthenticatedUser(pagination);
    }
}
