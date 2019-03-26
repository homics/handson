package com.homics.monolith.service;

import com.homics.monolith.model.Article;
import com.homics.monolith.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long articleId) {
        return articleRepository.findById(articleId).get();
    }
}
