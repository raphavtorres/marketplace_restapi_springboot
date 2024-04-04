package com.api.delivery.service;

import com.api.delivery.domain.order.Order;
import com.api.delivery.domain.order.OrderDetailDto;
import com.api.delivery.domain.order.OrderRegisterDto;
import com.api.delivery.domain.user.User;
import com.api.delivery.domain.user.UserRole;
import com.api.delivery.repository.OrderRepository;
import com.api.delivery.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public OrderDetailDto createOrder(OrderRegisterDto orderRegisterDto) {
        Order order = new Order(orderRegisterDto);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        order.setUser(user);

        orderRepository.save(order);
        return new OrderDetailDto(order);
    }

    public ResponseEntity<Page<OrderDetailDto>> GetOrderByAuthenticatedUser(Pageable pagination) {
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
