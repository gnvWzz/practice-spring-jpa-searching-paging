package com.example.democustomermanagement.service;

import com.example.democustomermanagement.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICustomerService {
    Iterable<Customer> findAll();
    Optional<Customer> findById(Long id);

    void save(Customer t);

    void remove(Long id);

    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findAllByName(String nameCustomer, Pageable pageable);
}
