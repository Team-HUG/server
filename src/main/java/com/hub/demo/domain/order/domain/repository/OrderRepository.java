package com.hub.demo.domain.order.domain.repository;

import com.hub.demo.domain.cart.domain.Cart;
import com.hub.demo.domain.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderItems_Cart(Cart cart);
}
