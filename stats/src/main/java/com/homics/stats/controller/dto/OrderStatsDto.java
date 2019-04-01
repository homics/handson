package com.homics.stats.controller.dto;

public class OrderStatsDto {
    private Long nbOrder;
    private Double avgPrice;

    public OrderStatsDto(Long nbOrder, Double avgPrice) {
        this.nbOrder = nbOrder;
        this.avgPrice = avgPrice;
    }

    public Long getNbOrder() {
        return nbOrder;
    }

    public void setNbOrder(Long nbOrder) {
        this.nbOrder = nbOrder;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }
}
