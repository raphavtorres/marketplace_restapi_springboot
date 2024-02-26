package com.api.delivery.controller;

import com.api.delivery.domain.order.Order;
import com.api.delivery.domain.order.OrderDetailDto;
import com.api.delivery.domain.order.OrderRegisterDto;
import com.api.delivery.domain.user.User;
import com.api.delivery.domain.user.UserRole;
import com.api.delivery.repository.OrderRepository;
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
    private OrderRepository orderRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid OrderRegisterDto orderRegisterDto, UriComponentsBuilder uriComponentsBuilder) {
        Order order = new Order(orderRegisterDto);
        orderRepository.save(order);

        var uri = uriComponentsBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(new OrderDetailDto(order));
    }

    @GetMapping
    public ResponseEntity<Page<OrderDetailDto>> list(Pageable pagination) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (authentication.getAuthorities().toString().contains(UserRole.ADMIN.toString())) {
            var page = orderRepository.findAll(pagination).map(OrderDetailDto::new);
            return ResponseEntity.ok(page);
        }
        var page = orderRepository.findAllByUserId(user.getId(), pagination);
        return ResponseEntity.ok(page);
    }
}
