package com.example.gutrend.repository;

import com.example.gutrend.model.SizeColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISizeColumnRepository extends JpaRepository<SizeColumn,Long> {
    Boolean existsByName(String name);
}
