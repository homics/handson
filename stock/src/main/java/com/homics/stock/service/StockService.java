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
        Long operationId = impactStockMessage.getOperationId();
        if (operationAlreadyProcess(operationId)) {
            return;
        }
        try {
            verifyAvailableStocks(impactStockMessage);
            impactStocks(impactStockMessage);
            stockAcknowledgmentProducer.notifyStockChanges(operationId, true);
            stockOperationDao.save(new StockOperation(operationId));
        } catch (ValidationException e) {
            stockAcknowledgmentProducer.notifyStockChanges(operationId, false);
            throw e;
        }
    }

    private boolean operationAlreadyProcess(Long operationId) {
        return stockOperationDao.existsById(operationId);
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
