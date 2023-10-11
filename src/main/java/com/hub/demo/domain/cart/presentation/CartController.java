package com.hub.demo.domain.cart.presentation;

import com.hub.demo.domain.cart.presentation.dto.request.CartFoodAddRequestDto;
import com.hub.demo.domain.cart.presentation.dto.response.CartListResponseDto;
import com.hub.demo.domain.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/food/add")
    public void addFood(@Valid @RequestBody CartFoodAddRequestDto requestDto) {
        cartService.addFood(requestDto);
    }

    @GetMapping("/food/list")
    public List<CartListResponseDto> getCartFoodList() {
        return cartService.getCartFoodList();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/food/delete/{id}")
    public void deleteFood(@PathVariable(name = "id") Long id) {
        cartService.deleteCartFood(id);
    }

    @PostMapping("/submit/order")
    public void submitOrder() {
        cartService.submitOrder();
    }
}
