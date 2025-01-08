package com.E_Commerce.customer_service.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.E_Commerce.customer_service.pojo.Product;

@FeignClient(name = "admin-service")
public interface ProductClient {

    @GetMapping("/admin/products")
    List<Product> getAllProducts();

    @GetMapping("/admin/products/{productId}")
    Product getProductById(@PathVariable int productId);
}


