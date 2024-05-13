package com.enoca.challenge.models.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order,Long>
{
    @Query("SELECT ord FROM Order ord JOIN ord.customer cs WHERE cs.customerId = :customerId AND ord.orderCode = :orderCode")
    List<Order> findAllByOrderCodeAndCustomerId(@Param("customerId") long customerId, @Param("orderCode") String orderCode);

    @Query("SELECT DISTINCT ord.orderCode FROM Order ord JOIN ord.customer cs WHERE cs.customerId = :customerId")
    List<String> findOrderCodeByCustomerId(@Param("customerId") long customerId);

    @Query("SELECT DISTINCT ord.orderCode FROM Order ord JOIN ord.customer cs")
    List<String> findAllOrderCode();
}
