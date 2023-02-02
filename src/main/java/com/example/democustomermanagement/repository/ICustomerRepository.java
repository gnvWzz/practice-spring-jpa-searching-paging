package com.example.democustomermanagement.repository;

import com.example.democustomermanagement.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    @Query(value = "select c from Customer as c where c.name like concat('%', ?1, '%')")
    Page<Customer> findAllByName(String nameCustomer, Pageable pageable);
}
