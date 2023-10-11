package com.hub.demo.domain.food.domain.repository;

import com.hub.demo.domain.food.domain.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    Page<Food> findByCategory(Pageable pageable, String category);
    @Query(value = "SELECT DISTINCT category FROM hub.food", nativeQuery = true)
    List<String> findDistinctByCategory();
}
