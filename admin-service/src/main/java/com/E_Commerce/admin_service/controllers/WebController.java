package com.E_Commerce.admin_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.E_Commerce.admin_service.pojo.Customer;
import com.E_Commerce.admin_service.pojo.Product;
import com.E_Commerce.admin_service.services.AdminService;

@Controller
@RequestMapping("/admin")
public class WebController {

    @Autowired
    private AdminService adminService;

    // Admin Dashboard View
    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin-home";  // This returns the admin-home.jsp
    }

    // Admin Page for viewing all products
    @GetMapping("/products/view")
    public String viewProducts(Model model) {
        List<Product> products = adminService.getAllProducts();
        model.addAttribute("products", products);
        return "view-products";  // This returns the view-products.jsp
    }

    // Admin Page for viewing all customers
    @GetMapping("/customers/view")
    public String viewCustomers(Model model) {
        List<Customer> customers = adminService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "view-customers";  // This returns the view-customers.jsp
    }

    // Admin Page for viewing all orders
    @GetMapping("/orders/view")
    public String viewOrders(Model model) {
        // You would need to implement an `Order` class and fetch orders accordingly
        // List<Order> orders = adminService.getAllOrders();
        // model.addAttribute("orders", orders);
        return "view-orders";  // This returns the view-orders.jsp
    }

    // Admin Page for adding a new product
    @GetMapping("/products/add")
    public String addProductPage() {
        return "add-product";  // This returns the add-product.jsp page where the admin can add a new product
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute Product product) {
        adminService.addProduct(product);
        return "redirect:/admin/products/view";  // Redirects to View Products page
    }

    // Admin Page for adding a new customer
    @GetMapping("/customers/add")
    public String addCustomerPage() {
        return "add-customer";  // This returns the add-customer.jsp page
    }

    @PostMapping("/customers/add")
    public String addCustomer(@ModelAttribute Customer customer) {
        adminService.addCustomer(customer);
        return "redirect:/admin/customers/view";  // Redirects to View Customers page
    }

    // Admin Page for deleting a product
    @PostMapping("/products/delete/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        adminService.removeProduct(productId);
        return "redirect:/admin/products/view";  // Redirects to View Products page
    }

    // Admin Page for deleting a customer
    @PostMapping("/customers/delete/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {
        adminService.removeCustomer(customerId);
        return "redirect:/admin/customers/view";  // Redirects to View Customers page
    }
}