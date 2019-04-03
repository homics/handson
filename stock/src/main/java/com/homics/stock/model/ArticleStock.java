package com.homics.stock.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
public class ArticleStock {

    @Id
    private Long articleId;
    @Min(value = 0L, message = "The stock of a product must be positive")
    private Long stock;

    public ArticleStock() {
    }

    public ArticleStock(Long articleId, Long stock) {
        this.articleId = articleId;
        this.stock = stock;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
