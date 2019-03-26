package com.homics.monolith.repository;

import com.homics.monolith.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Modifying
    @Query("UPDATE Article SET stock = stock - ?2 WHERE id = ?1")
    void decrementStock(Long ArticleId, Integer quantity);
}
