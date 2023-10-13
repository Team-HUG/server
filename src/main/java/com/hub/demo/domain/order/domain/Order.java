package com.hub.demo.domain.order.domain;

import com.hub.demo.domain.cart.domain.CartItem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "food_order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CartItem> orderItems = new ArrayList<>();

    private LocalDateTime createDate = LocalDateTime.now();

    @Column(columnDefinition = "TINYINT(1) default 0")
    private Boolean isComplete = false;

    public Order(List<CartItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void changeIsComplete() {
        this.isComplete = !isComplete;
    }
}
