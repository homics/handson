package com.homics.monolith.repository;

import com.homics.monolith.model.OrderPayMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatsRepository extends JpaRepository<OrderPayMessage, Long> {
}
