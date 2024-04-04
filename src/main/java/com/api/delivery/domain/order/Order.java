package com.api.delivery.domain.order;

import com.api.delivery.domain.user.User;
import com.api.delivery.repository.UserRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "orders")
@Entity(name = "Order")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order {
//    @Autowired
//    UserRepository userRepository;

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Order(OrderRegisterDto orderDto) {
        this.title = orderDto.title();
        this.price = orderDto.price();
        this.quantity = orderDto.quantity();
        this.thumbnail = orderDto.thumbnail();
        this.totalPrice = orderDto.totalPrice();
    }

    public void setUser(User user) {
        this.user = user;
    }
}