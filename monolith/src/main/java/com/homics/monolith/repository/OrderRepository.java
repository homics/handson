package com.homics.monolith.repository;

import com.homics.monolith.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("FROM Order WHERE status = 'PENDING' and user = ?1")
    Order getCurrentOrder(String user);

    @Query("select avg(ord.totalPrice) from Order ord where user = ?1  and status = 'PAYED'")
    Double getOrderAvg(String user);

    @Query("select count(ord) from Order ord where user = ?1  and status = 'PAYED'")
    Long getOrderCount(String user);

    @Query("FROM Order WHERE status = 'PAYED' and user = ?1")
    List<Order> getPayedOrder(String user);
}
