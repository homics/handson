package com.homics.stock.service;

import com.homics.messaging.model.ArticleStockDto;
import com.homics.messaging.model.ImpactStockMessage;
import com.homics.stock.dao.StockDao;
import com.homics.stock.dao.StockOperationDao;
import com.homics.stock.model.ArticleStock;
import com.homics.stock.model.StockOperation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.List;

@Service
public class StockService {

    private StockAcknowledgmentProducer stockAcknowledgmentProducer;
    private StockOperationDao stockOperationDao;
    private StockDao stockDao;

    public StockService(StockOperationDao stockOperationDao, StockDao stockDao, StockAcknowledgmentProducer stockAcknowledgmentProducer) {
        this.stockOperationDao = stockOperationDao;
        this.stockDao = stockDao;
        this.stockAcknowledgmentProducer = stockAcknowledgmentProducer;
    }

    public List<ArticleStock> getStocks() {
        return stockDao.findAll();
    }

    @Transactional
    public void impactStock(ImpactStockMessage impactStockMessage) {
        if (operationAlreadyProcess(null)) {
            return;
        }
        try {
            verifyAvailableStocks(impactStockMessage);
            impactStocks(impactStockMessage);
            // TODO 6
            //  notify kafka that the stock changes has been made.
        } catch (ValidationException e) {
            // TODO 7
            //  notify kafka that the stock changes couldn't be done.
        } finally {
            // TODO 8
            //  save the operation as processed, so it won't be process several times.
        }
    }

    private boolean operationAlreadyProcess(Long operationId) {
        // TODO 5
        //  verify if the operation wasn't already process
        return false;
    }

    private void verifyAvailableStocks(ImpactStockMessage impactStockMessage) throws ValidationException {
        for (ArticleStockDto articleStockDto : impactStockMessage.getArticleStocks()) {
            ArticleStock article = stockDao.getOne(articleStockDto.getArticleId());
            if (article.getStock() + articleStockDto.getDeltaStock() < 0) {
                throw new ValidationException("Not enough stock");
            }
        }
    }

    private void impactStocks(ImpactStockMessage impactStockMessage) {
        impactStockMessage.getArticleStocks().forEach(this::impactStock);
    }

    private void impactStock(ArticleStockDto articleStockDto) {
        ArticleStock articleStock = stockDao.getOne(articleStockDto.getArticleId());
        articleStock.setStock(articleStock.getStock() + articleStockDto.getDeltaStock());
        stockDao.save(articleStock);
    }
}
