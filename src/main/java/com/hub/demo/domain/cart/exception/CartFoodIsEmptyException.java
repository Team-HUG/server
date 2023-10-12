package com.hub.demo.domain.cart.exception;

import com.hub.demo.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class CartFoodIsEmptyException extends CustomException {
    public static final CustomException EXCEPTION = new CartFoodIsEmptyException();

    private CartFoodIsEmptyException() {
        super(HttpStatus.NOT_FOUND, "장바구니에 아무것도 없습니다.");
    }
}
