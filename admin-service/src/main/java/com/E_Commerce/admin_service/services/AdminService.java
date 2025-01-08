package com.E_Commerce.admin_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.E_Commerce.admin_service.pojo.Customer;
import com.E_Commerce.admin_service.pojo.Product;
import com.E_Commerce.admin_service.repositories.CustomerRepository;
import com.E_Commerce.admin_service.repositories.ProductRepository;


@Service
public class AdminService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Method to add a new product to the product list
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Method to get all products from the product list
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Method to remove a product by product ID
    public String removeProduct(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            productRepository.deleteById(productId);
            return "Product removed successfully!";
        } else {
            return "Product not found!";
        }
    }

    // Method to add a new customer
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Method to remove a customer by customer ID
    public String removeCustomer(int customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            customerRepository.deleteById(customerId);
            return "Customer removed successfully!";
        } else {
            return "Customer not found!";
        }
    }

    public Optional<Customer> getCustomerById(int customerId) {
        return customerRepository.findById(customerId);
    }
    
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElse(null); // Adjust based on your repository logic
    }
}