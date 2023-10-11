package com.hub.demo.domain.cart.exception;

import com.hub.demo.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class CartFoodNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new CartFoodNotFoundException();

    private CartFoodNotFoundException() {
        super(HttpStatus.NOT_FOUND, "장바구니에 이 음식이 삭제되었거나 존재하지 않습니다.");
    }
}
