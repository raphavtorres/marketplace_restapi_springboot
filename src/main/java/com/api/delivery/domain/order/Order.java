package com.api.delivery.domain.order;

import com.api.delivery.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "orders")
@Entity(name = "Order")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private BigDecimal price;
    private Integer quantity;
    private String thumbnail;
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    @Lob
//    private Byte[] thumbnail;


    public Order(OrderRegisterDto data) {
        this.title = data.title();
        this.price = data.price();
        this.quantity = data.quantity();
        this.thumbnail = data.thumbnail();
        this.totalPrice = data.totalPrice();
    }

    public Order() {}
}