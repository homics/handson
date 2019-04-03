package com.homics.stock.dao;

import com.homics.stock.model.ArticleStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDao extends JpaRepository<ArticleStock, Long> {
}
