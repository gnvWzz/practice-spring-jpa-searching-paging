package com.example.democustomermanagement.repository;

import com.example.democustomermanagement.model.Customer;
import com.example.democustomermanagement.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Iterable<Customer> findAllByProvince(Province province);

    @Query(value = "select * from customer where customer_name like %:name%", nativeQuery = true)
    Page<Customer> findAllByName(@Param("name") String name, Pageable pageable);
}
