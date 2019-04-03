package com.homics.stock.dao;

import com.homics.stock.model.StockOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockOperationDao extends JpaRepository<StockOperation, Long> {
}
