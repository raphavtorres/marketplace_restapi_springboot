package com.api.delivery.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Table(name = "doctors")
@Entity(name = "Doctor")
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
}