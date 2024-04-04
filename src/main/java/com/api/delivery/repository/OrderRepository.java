package com.api.delivery.repository;

import com.api.delivery.domain.order.Order;
import com.api.delivery.domain.order.OrderDetailDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface OrderRepository extends PagingAndSortingRepository<Order, String> {
    @Query("""
            SELECT o FROM Order o
            WHERE o.user.id = :id
            """)
    Page<OrderDetailDto> findAllByUserId(UUID id, Pageable pageable);

    void save(Order order);
}
