package com.hub.demo.domain.food.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "food")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String foodName;

    private String price;

    private String image;

    @Builder
    public Food(String category, String foodName, String price, String image) {
        this.category = category;
        this.foodName = foodName;
        this.price = price;
        this.image = image;
    }
}
