package com.E_Commerce.admin_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.E_Commerce.admin_service.pojo.Customer;
import com.E_Commerce.admin_service.pojo.Product;
import com.E_Commerce.admin_service.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Add a new product
    @PostMapping("/addnewproducts")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product createdProduct = adminService.addProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Get all products
    @GetMapping("/getallproducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = adminService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get product by ID
    @GetMapping("/getbyproductid/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        Product product = adminService.getProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // Remove a product by ID
    @DeleteMapping("/deleteproduct/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable int productId) {
        String response = adminService.removeProduct(productId);
        if (response.equals("Product not found!")) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Add a new customer
    @PostMapping("/addnewcustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = adminService.addCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    // Get all customers
    @GetMapping("/getallcustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = adminService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // Remove a customer by ID
    @DeleteMapping("/deletecustomer/{customerId}")
    public ResponseEntity<String> removeCustomer(@PathVariable int customerId) {
        String response = adminService.removeCustomer(customerId);
        if (response.equals("Customer not found!")) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}