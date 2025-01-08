package com.E_Commerce.customer_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.E_Commerce.customer_service.pojo.Cart;
import com.E_Commerce.customer_service.pojo.Customer;
import com.E_Commerce.customer_service.pojo.Product;
import com.E_Commerce.customer_service.repositories.CartItemRepository;
import com.E_Commerce.customer_service.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartItemRepository cartRepository;

    @Autowired
    private ProductClient productClient;  // Feign Client for calling Admin Service's Product API

    // Add a product to the cart for the customer
    public String addProductToCart(int customerId, int productId, int quantity) {
        try {
            // Fetch the customer
            Optional<Customer> customerOptional = customerRepository.findById(customerId);
            if (!customerOptional.isPresent()) {
                return "Customer not found!";
            }
            Customer customer = customerOptional.get();

            // Fetch product details from admin service using Feign client
            Product product = productClient.getProductById(productId);
            if (product == null) {
                return "Product not found!";
            }

            // Create a new Cart entry for the customer with the selected product and quantity
            Cart cart = new Cart();
            cart.setCustomer(customer);
            cart.setProduct(product);
            cart.setQuantity(quantity);
            cart.setTotalPrice(product.getPrice() * quantity);  // Calculate total price based on quantity

            // Save the cart to the database
            cartRepository.save(cart);

            return "Product added to cart successfully!";
        } catch (Exception e) {
            // Log the error with message
            e.printStackTrace();
            return "An error occurred while adding product to the cart: " + e.getMessage();
        }
    }

    // Display the list of available products
    public List<Product> getAllProducts() {
        return productClient.getAllProducts();  // Call the Admin Service to get products
    }

    // View all products in the customer's cart
    public List<Cart> getCartProducts(int customerId) {
        return cartRepository.findByCustomer_CustomerId(customerId);  // Get cart items for the customer
    }

    // Display the total bill (sum of prices of all products in the cart)
    public double getBill(int customerId) {
        List<Cart> cartItems = cartRepository.findByCustomer_CustomerId(customerId);
        double totalBill = 0;

        for (Cart cart : cartItems) {
            totalBill += cart.getTotalPrice();  // Sum the total price of all cart items
        }

        return totalBill;
    }

    // Add balance to customer's wallet
    public String addBalanceToWallet(int customerId, double amount) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (!customerOptional.isPresent()) {
            return "Customer not found!";
        }
        Customer customer = customerOptional.get();
        
        // Add the balance to the wallet
        customer.setWalletBalance(customer.getWalletBalance() + amount);
        customerRepository.save(customer);

        return "Balance added successfully!";
    }

    // Method to check if the customer has sufficient balance to pay the bill
    public boolean hasSufficientBalance(int customerId) {
        double billAmount = getBill(customerId);
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (!customerOptional.isPresent()) {
            return false;  // Customer not found
        }
        Customer customer = customerOptional.get();
        return customer.getWalletBalance() >= billAmount;
    }

    // Method to deduct amount from the wallet after successful purchase
    public String deductAmountFromWallet(int customerId) {
        double billAmount = getBill(customerId);
        if (hasSufficientBalance(customerId)) {
            Optional<Customer> customerOptional = customerRepository.findById(customerId);
            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                customer.setWalletBalance(customer.getWalletBalance() - billAmount);  // Deduct the bill amount
                customerRepository.save(customer);

                

                return "Payment successful! Amount deducted from wallet.";
            }
        }
        return "Insufficient wallet balance!";
    }
}