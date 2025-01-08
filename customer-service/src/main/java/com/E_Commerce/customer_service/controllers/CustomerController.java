package com.E_Commerce.customer_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.E_Commerce.customer_service.pojo.Cart;
import com.E_Commerce.customer_service.pojo.Product;
import com.E_Commerce.customer_service.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/{customerId}/cart/{productId}")
    public String addProductToCart(
            @PathVariable int customerId,
            @PathVariable int productId,
            @RequestParam int quantity) {
        return customerService.addProductToCart(customerId, productId, quantity);
    }

    @GetMapping("/productscust")
    public List<Product> getAllProducts() {
        return customerService.getAllProducts();
    }

    @GetMapping("/{customerId}/cart")
    public List<Cart> getCartProducts(@PathVariable int customerId) {
        return customerService.getCartProducts(customerId);
    }

    @GetMapping("/{customerId}/bill")
    public double getBill(@PathVariable int customerId) {
        return customerService.getBill(customerId);
    }

    @PostMapping("/{customerId}/wallet")
    public String addBalanceToWallet(
            @PathVariable int customerId,
            @RequestParam double amount) {
        return customerService.addBalanceToWallet(customerId, amount);
    }
    
    @PostMapping("/{customerId}/pay")
    public String payBill(@PathVariable int customerId) {
        return customerService.deductAmountFromWallet(customerId);
    }
}