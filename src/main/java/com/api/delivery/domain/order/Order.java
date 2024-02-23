package com.api.delivery.model;

import com.api.delivery.dto.OrderRegisterDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Table(name = "orders")
@Entity(name = "Order")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private BigDecimal price;
    private Integer amount;
    private String thumbnail;
//    @Lob
//    private Byte[] thumbnail;


    public Order(OrderRegisterDto data) {
        this.title = data.title();
        this.price = data.price();
        this.amount = data.amount();
        this.thumbnail = data.thumbnail();
    }

    public Order() {}
}