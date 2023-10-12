package com.hub.demo.domain.cart.domain.repository;

import com.hub.demo.domain.cart.domain.Cart;
import com.hub.demo.domain.cart.domain.CartItem;
import com.hub.demo.domain.food.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCartAndIsOrder(Cart cart, boolean isOrder);

    CartItem findByCartAndFoodAndIsOrder(Cart cart, Food food, boolean isOrder);

    Boolean existsByFoodAndIsOrder(Food food, boolean isOrder);

    Boolean existsByCartAndIsOrder(Cart cart, boolean isOrder);
}
