package com.E_Commerce.admin_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.E_Commerce.admin_service.pojo.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
