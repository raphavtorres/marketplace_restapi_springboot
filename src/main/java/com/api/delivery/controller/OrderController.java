package com.api.delivery.controller;

import com.api.delivery.dto.Order;
import com.api.delivery.dto.OrderDetailDto;
import com.api.delivery.dto.OrderRegisterDto;
import com.api.delivery.repository.OrderRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid OrderRegisterDto orderRegisterDto, UriComponentsBuilder uriComponentsBuilder) {
        Order order = new Order(orderRegisterDto);
        orderRepository.save(order);

        var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(new OrderDetailDto(order));
    }
}
