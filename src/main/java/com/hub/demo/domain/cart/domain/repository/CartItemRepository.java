package com.hub.demo.domain.cart.domain.repository;

import com.hub.demo.domain.cart.domain.Cart;
import com.hub.demo.domain.cart.domain.CartItem;
import com.hub.demo.domain.food.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCart(Cart cart);

    CartItem findByCartAndFood(Cart cart, Food food);

    Boolean existsByFood(Food food);

    Boolean existsByCart(Cart cart);
}
