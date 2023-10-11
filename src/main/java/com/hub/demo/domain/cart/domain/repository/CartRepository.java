package com.hub.demo.domain.cart.domain.repository;

import com.hub.demo.domain.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByTableNumber(int tableNumber);
}
