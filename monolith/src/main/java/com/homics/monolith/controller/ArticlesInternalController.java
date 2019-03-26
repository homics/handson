package com.homics.monolith.controller;

import com.homics.monolith.model.Article;
import com.homics.monolith.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mono/internal/articles")
public class ArticlesInternalController {

    private ArticleService articleService;

    public ArticlesInternalController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(produces = "application/json")
    public List<Article> getArticles() {
        return articleService.getArticles();
    }
}
