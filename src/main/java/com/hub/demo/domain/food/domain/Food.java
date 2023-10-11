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

    private int price;

    @Column(columnDefinition = "TINYINT(1) default 0")
    private Boolean isEvent;

    private String imageUrl;

    @Builder
    public Food(String category, String foodName, int price) {
        this.category = category;
        this.foodName = foodName;
        this.price = price;
    }

    public void injectImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void changeEvent() {
        this.isEvent = !isEvent;
    }
}
