package com.homics.stats.repository;

import com.homics.stats.model.OrderStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatsRepository extends JpaRepository<OrderStats, Long> {

    @Query("select avg(ord.orderPrice) from OrderStats ord where user = ?1")
    Double getOrderAvg(String user);

    @Query("select count(ord) from OrderStats ord where user = ?1")
    Long getOrderCount(String user);
}
