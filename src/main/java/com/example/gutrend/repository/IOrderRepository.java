package com.example.gutrend.repository;

import com.example.gutrend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
//    Boolean existsByName(String name);
}