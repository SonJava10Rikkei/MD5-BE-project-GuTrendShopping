package com.example.gutrend.repository;

import com.example.gutrend.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Boolean existsByName(String name);
}
