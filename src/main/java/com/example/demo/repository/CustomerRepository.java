package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Customer;

public interface CustomerRepository {

    int save(Customer cus);

    int update(Customer cus);

    Customer findById(Long id);

    int deleteById(Long id);

    List<Customer> findAll();

    List<Customer> findByPublished(boolean published);

    List<Customer> findByTitleContaining(String title);

    int deleteAll();

}
