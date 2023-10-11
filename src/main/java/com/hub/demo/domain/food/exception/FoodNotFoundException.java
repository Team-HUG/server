package com.hub.demo.domain.food.exception;

import com.hub.demo.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class FoodNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new FoodNotFoundException();

    private FoodNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 음식이 존재하지 않습니다.");
    }
}
