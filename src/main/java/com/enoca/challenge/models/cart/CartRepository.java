package com.enoca.challenge.models.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,Long>{
    @Query("SELECT ct FROM Cart ct JOIN ct.customer cs WHERE cs.customerId = :customerId")
    List<Cart> findCartsByCustomerId(@Param("customerId") long customerId);

    @Query("SELECT ct FROM Cart ct JOIN ct.customer cs JOIN ct.product pr WHERE cs.customerId = :customerId AND pr.productId = :productId")
    Cart findCartByCustomerIdAndProductId(@Param("customerId") long customerId, @Param("productId") long productId);
}
