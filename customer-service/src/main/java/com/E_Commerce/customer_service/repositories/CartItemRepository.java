package com.E_Commerce.customer_service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.E_Commerce.customer_service.pojo.Cart;

public interface CartItemRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByCustomer_CustomerId(Integer customerId);
}
