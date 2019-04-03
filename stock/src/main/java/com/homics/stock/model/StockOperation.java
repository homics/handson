package com.homics.stock.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StockOperation {

    @Id
    private Long operationId;

    public StockOperation() {
    }

    public StockOperation(Long operationId) {
        this.operationId = operationId;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }
}
