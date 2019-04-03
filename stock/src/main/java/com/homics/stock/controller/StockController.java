package com.homics.stock.controller;

import com.homics.stock.model.ArticleStock;
import com.homics.stock.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("stock")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/api")
    public List<ArticleStock> getStocks() {
        return stockService.getStocks();
    }
}
