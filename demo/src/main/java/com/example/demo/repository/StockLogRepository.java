package com.example.demo.repository;

import com.example.demo.entity.StockLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockLogRepository extends JpaRepository<StockLog, Long> {
}